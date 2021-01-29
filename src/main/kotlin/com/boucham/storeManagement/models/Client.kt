package com.boucham.storeManagement.models;

import com.boucham.storeManagement.models.actions.Visited
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "clients")
data class Client(
        @Id val id: String? = null,
        @Field val storesVisited: MutableList<Visited>?,
        @Field val firstName: String,
        @Field val lastName: String
)


