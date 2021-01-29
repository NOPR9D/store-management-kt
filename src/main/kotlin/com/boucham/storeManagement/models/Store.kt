package com.boucham.storeManagement.models;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "stores")
data class Store(
        @Id val id: String? = null,
        @Field val name: String,
)
