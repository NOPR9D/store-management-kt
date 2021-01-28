package com.boucham.storeManagement.models;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "clients")
data class Client(
        @Id val id: String,
        val storesVisited: Array<String>,
        val firstName: String,
        val lastName: String
) {

}
