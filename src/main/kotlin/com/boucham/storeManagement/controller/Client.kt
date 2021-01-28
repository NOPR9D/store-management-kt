package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.repository.ClientRepository
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/clients")
class ClientController(@Autowired val repository: ClientRepository) {
    val logger = LogFactory.getLog(ClientController::class.java)

    @GetMapping()
    fun getClients(): List<Client> = repository.findAll()

    @GetMapping("/{id}")
    fun getClient(@PathVariable id: String, response: HttpServletResponse): Optional<Client>? {
        val result = repository.findById(id);
        if (result.isEmpty) return notFound(response);
        return result
    }

    @PostMapping()
    fun createClient(@RequestBody client: Client, response: HttpServletResponse): String? {
        val newClient = repository.insert(client)
        return added(response)
    }

    @DeleteMapping("/{id}")
    fun removeClient(@PathVariable id: String, response: HttpServletResponse): Optional<Client>? {
        val result = repository.findById(id);
        if (result.isEmpty) return notFound(response);
        return result
    }
}