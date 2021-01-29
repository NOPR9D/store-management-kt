package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.actions.Visited
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.boucham.storeManagement.services.notFound
import com.boucham.storeManagement.services.visited
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.Instant
import javax.servlet.http.HttpServletResponse


// Endpoint actions (Visite)
@RestController
@RequestMapping("/api/actions")
class ActionController(@Autowired val clientRepository: ClientRepository, @Autowired val storeRepository: StoreRepository) {
    val logger = LogFactory.getLog(ClientController::class.java)

    // Visiter un magasin (Ajoute une relation client > magasin dans la liste "storesVisited" du client
    @PostMapping("/visite")
    fun visiteStore(@RequestBody visiteAction: Visited, response: HttpServletResponse): String? {
        val client = clientRepository.findById(visiteAction.clientId)
        val store = storeRepository.findById(visiteAction.storeId)

        if (client.isEmpty and store.isEmpty) return notFound(response);

        val _client = client.get().copy()

        _client.storesVisited!!.add(visiteAction)
        clientRepository.save(_client)

        logger.info("Client under id: ${visiteAction.clientId} visited Store under id : ${visiteAction.storeId}")
        return visited(client.get().id as String, store.get().id as String, response)
    }
}