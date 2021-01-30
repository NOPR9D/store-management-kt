package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.services.added
import com.boucham.storeManagement.services.alreadyExist
import com.boucham.storeManagement.services.deleted
import com.boucham.storeManagement.services.notFound
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


// Endpoint Clients
@RestController
@RequestMapping("/api/clients")
class ClientController(@Autowired val clientRepository: ClientRepository) {
    val logger = LogFactory.getLog(ClientController::class.java)

    // Info de tout les clients
    @ApiOperation(value = "Get all clients info")
    @GetMapping()
    fun getClients(): List<Client> = clientRepository.findAll()

    // Info d'un client par id
    @ApiOperation(value = "Get client info by id")
    @GetMapping("/{id}")
    fun getClient(@PathVariable id: String, response: HttpServletResponse): Client? {
        val result = clientRepository.findById(id);

        logger.info("Return client id: $id info")

        if (result.isEmpty) return notFound(response);
        return result.get()
    }

    // Creer un client
    @ApiOperation(value = "Create a client")
    @ApiParam(name = "test",value = "test")
    @PostMapping()
    fun createClient(@RequestBody client: Client, response: HttpServletResponse): String {
        clientRepository.findAll().forEach {
            if ((it.firstName == client.firstName) and (it.lastName == client.lastName)) return alreadyExist(response)
        }

        val newClient = clientRepository.insert(client)

        logger.info("Client : ${newClient.firstName}, ${newClient.lastName} added under id: ${newClient.id}")

        return added(newClient.id as String, response)

    }

    // Retirer un client (par id)
    @ApiOperation(value = "Delete client by id")
    @DeleteMapping("/{id}")
    fun removeClient(@PathVariable id: String, response: HttpServletResponse): String? {
        val result = clientRepository.findById(id);

        return if (!result.isEmpty) {
            clientRepository.delete(result.get())
            logger.info("Client id: ${result.get().id} deleted")
            deleted(id as String, response)

        } else {
            notFound(response);
        }

    }
}