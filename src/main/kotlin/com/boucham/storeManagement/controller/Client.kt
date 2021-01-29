package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.services.added
import com.boucham.storeManagement.services.deleted
import com.boucham.storeManagement.services.notFound
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


// Endpoint Clients
@RestController
@RequestMapping("/api/clients")
class ClientController(@Autowired val repository: ClientRepository) {
    val logger = LogFactory.getLog(ClientController::class.java)

    // Info de tout les clients
    @GetMapping()
    fun getClients(): List<Client> = repository.findAll()

    // Info d'un client par id
    @GetMapping("/{id}")
    fun getClient(@PathVariable id: String, response: HttpServletResponse): Client? {
        val result = repository.findById(id);

        logger.info("Return client id: $id info")

        if (result.isEmpty) return notFound(response);
        return result.get()
    }

    // Creer un client
    @PostMapping()
    fun createClient(@RequestBody client: Client, response: HttpServletResponse): String? {
        val newClient = repository.insert(client)

        logger.info("Client : ${newClient.firstName}, ${newClient.lastName} added under id: ${newClient.id}")

        return added(newClient.id as String, response)
    }

    // Retirer un client (par id)
    @DeleteMapping("/{id}")
    fun removeClient(@PathVariable id: String, response: HttpServletResponse): String? {
        val result = repository.findById(id);

        if (!result.isEmpty) {
            repository.delete(result.get())
            logger.info("Client id: ${result.get().id} deleted")
        } else {
            return notFound(response);
        }

        return deleted(id as String, response)
    }
}