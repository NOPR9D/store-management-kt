package com.boucham.storeManagement.repository

import com.boucham.storeManagement.models.Store
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

// db magasin
@Repository
interface StoreRepository : MongoRepository<Store, String> {
    override fun findById(id: String): Optional<Store>

}