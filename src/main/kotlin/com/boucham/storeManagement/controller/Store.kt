package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Store
import com.boucham.storeManagement.repository.StoreRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/stores")
class StoreController(@Autowired val repository: StoreRepository) {
    val logger = LogFactory.getLog(StoreController::class.java)

    @GetMapping()
    fun getStores(): List<Store> = repository.findAll()

    @GetMapping("/{id}")
    fun getStore(@PathVariable id: String, response: HttpServletResponse): Store? {
        val result = repository.findById(id);
        logger.info("Return store id: ${id} info")
        if (result.isEmpty) return notFound(response);
        return result.get()
    }

    @PostMapping()
    fun createStore(@RequestBody store: Store, response: HttpServletResponse): String? {
        val newStore = repository.insert(store)
        logger.info("Store : ${newStore.name} added under id: ${newStore.id}")
        return added(response)
    }

    @DeleteMapping("/{id}")
    fun removeStore(@PathVariable id: String, response: HttpServletResponse): String? {
        val result = repository.findById(id);
        if (!result.isEmpty) {
            repository.delete(result.get())
            logger.info("Store id: ${result.get().id} deleted")
        } else {
            return notFound(response);
        }
        return deleted(response)
    }
}