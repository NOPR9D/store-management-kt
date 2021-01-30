package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Store
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.boucham.storeManagement.services.added
import com.boucham.storeManagement.services.alreadyExist
import com.boucham.storeManagement.services.deleted
import com.boucham.storeManagement.services.notFound
import io.swagger.annotations.ApiOperation
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


// Endpoint Magasins
@RestController
@RequestMapping("/api/stores")
class StoreController(@Autowired val storeRepository: StoreRepository, @Autowired val clientRepository: ClientRepository) {
    val logger = LogFactory.getLog(StoreController::class.java)

    // Info de tout les magasins
    @ApiOperation(value = "Get all stores info")
    @GetMapping()
    fun getStores(): List<Store> = storeRepository.findAll()

    // Info d'un magasin (par id)
    @ApiOperation(value = "Get store info by id")
    @GetMapping("/{id}")
    fun getStore(@PathVariable id: String, response: HttpServletResponse): Store? {
        val result = storeRepository.findById(id);
        val clients = clientRepository.findAll()

        val mappedResult = clients.map { client -> client.linkWith?.filter { it == id } }
        logger.info(mappedResult)
        logger.info("Return store id: $id info")
        if (result.isEmpty) return notFound(response);
        return result.get()
    }

    // Creer un magasin
    @ApiOperation(value = "Create a store")
    @PostMapping()
    fun createStore(@RequestBody store: Store, response: HttpServletResponse): String? {
        storeRepository.findAll().forEach {
            if (it.name == store.name) return alreadyExist(response)
        }

        val newStore = storeRepository.insert(store)

        logger.info("Store : ${newStore.name} added under id: ${newStore.id}")

        return added(newStore.id as String, response)
    }


    // Retirer un magasin (on retire aussi toutes les références a ce magasin dans la liste "link" des clients)
    @ApiOperation(value = "Remove a store by id")
    @DeleteMapping("/{id}")
    fun removeStore(@PathVariable id: String, response: HttpServletResponse): String? {
        val result = storeRepository.findById(id);
        if (!result.isEmpty) {

            val clients = clientRepository.findAll()

            clients.forEach { client ->
                val linkedList: List<String>? = client.linkWith?.filter { it == id }
                linkedList?.forEach { link ->
                    var isRemoved = client.linkWith.remove(link)
                    if (isRemoved) {
                        clientRepository.save(client)
                        logger.info("Client id: ${client.id} edited")
                    }
                }
            }

            storeRepository.delete(result.get())

            logger.info("Store id: ${result.get().id} deleted")
        } else {
            return notFound(response);
        }
        return deleted(id, response)
    }
}