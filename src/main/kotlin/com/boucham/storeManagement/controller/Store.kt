package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.models.Store
import com.boucham.storeManagement.models.actions.Visited
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.boucham.storeManagement.services.added
import com.boucham.storeManagement.services.deleted
import com.boucham.storeManagement.services.notFound
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
    @GetMapping()
    fun getStores(): List<Store> = storeRepository.findAll()

    // Info d'un magasin (par id)
    @GetMapping("/{id}")
    fun getStore(@PathVariable id: String, response: HttpServletResponse): Store? {
        val result = storeRepository.findById(id);
        logger.info("Return store id: $id info")
        if (result.isEmpty) return notFound(response);
        return result.get()
    }

    // Creer un magasin
    @PostMapping()
    fun createStore(@RequestBody store: Store, response: HttpServletResponse): String? {
        val newStore = storeRepository.insert(store)
        logger.info("Store : ${newStore.name} added under id: ${newStore.id}")
        return added(newStore.id as String, response)
    }


    // Retirer un magasin (on retire aussi toutes les références a ce magasin dans la liste "storesVisited" des clients)
    @DeleteMapping("/{id}")
    fun removeStore(@PathVariable id: String, response: HttpServletResponse): String? {
        val result = storeRepository.findById(id);
        if (!result.isEmpty) {

            val clients = clientRepository.findAll()

            clients.forEach { it ->
                val visited: Visited? = it.storesVisited.find { it.storeId == id }
                if (visited !== null) it.storesVisited.remove(visited)
                logger.info("Client id: ${it.id} edited")
                clientRepository.save(it)
            }

            storeRepository.delete(result.get())

            logger.info("Store id: ${result.get().id} deleted")
        } else {
            return notFound(response);
        }
        return deleted(id, response)
    }
}