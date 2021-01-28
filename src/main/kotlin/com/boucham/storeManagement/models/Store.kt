package com.boucham.storeManagement.models;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stores")
data class Store(
        @Id val id: String,
        val name: String,
) {

}
