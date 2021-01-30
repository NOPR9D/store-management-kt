package com.boucham.storeManagement.controller

import com.boucham.storeManagement.models.Link
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.boucham.storeManagement.services.alreadyExist
import com.boucham.storeManagement.services.linked
import com.boucham.storeManagement.services.notFound
import com.boucham.storeManagement.services.unlinked
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


// Endpoint actions (Visite)
@RestController
@RequestMapping("/api/actions")
class ActionController(@Autowired val clientRepository: ClientRepository, @Autowired val storeRepository: StoreRepository) {
    val logger = LogFactory.getLog(ClientController::class.java)

    // Visiter un magasin (Ajoute une relation client > magasin dans la liste "storesVisited" du client
    @ApiOperation(value = "Link User with Store")
    @ApiResponses(
            ApiResponse(code = 200, message = "Link client under id: clientId  with Store under id: StoreId\""),
            ApiResponse(code = 409, message = "Data Already Exist"),
            ApiResponse(code = 404, message = "Not found"),
            )
    @PostMapping("/link")
    fun linkWithStore(@RequestBody link: Link, response: HttpServletResponse): String? {
        val client = clientRepository.findById(link.clientId)
        val store = storeRepository.findById(link.storeId)

        if (client.isEmpty and store.isEmpty) return notFound(response);

        val _client = client.get().copy()

        val isAlreadyLinked = _client.linkWith?.find { it == store.get().id }

        return if (isAlreadyLinked.equals(null)) {
            _client.linkWith?.add(link.storeId)
            clientRepository.save(_client)
            logger.info("Link beetwen client under id: ${link.clientId} and store under id : ${link.storeId}")
            linked(client.get().id as String, store.get().id as String, response)
        } else {
            alreadyExist(response)
        }
    }

    @ApiOperation(value = "Unlink relation beetwen User and Store")
    @PostMapping("/unlink")
    fun unlinkWithStore(@RequestBody link: Link, response: HttpServletResponse): String? {
        val client = clientRepository.findById(link.clientId)
        val store = storeRepository.findById(link.storeId)
        logger.info(client)
        logger.info(store)
        if (client.isEmpty and store.isEmpty) return notFound(response)

        val _client = client.get().copy()

        val isLinked = _client.linkWith?.find { it == store.get().id }

        return if (!isLinked.equals(null)) {
            _client.linkWith?.remove(link.storeId)
            clientRepository.save(_client)
            logger.info("Unlink beetwen client under id: ${link.clientId} and store under id : ${link.storeId}")
            unlinked(client.get().id as String, store.get().id as String, response)
        } else {
            notFound(response)
        }


    }
}