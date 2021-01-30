package com.boucham.storeManagement.repository

import com.boucham.storeManagement.models.Client
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

// db client
interface ClientRepository : MongoRepository<Client, String> {

    override fun findById(id: String): Optional<Client>


}

