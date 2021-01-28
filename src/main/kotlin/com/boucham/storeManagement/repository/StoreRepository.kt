package com.boucham.storeManagement.repository

import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.models.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StoreRepository : MongoRepository<Store, String> {
    override fun findById(id: String): Optional<Store>

}