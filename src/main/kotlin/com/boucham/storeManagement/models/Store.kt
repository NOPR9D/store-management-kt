package com.boucham.storeManagement.models;

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

// Model magasin
@Document(collection = "stores")
data class Store(
        @JsonProperty("id")  @Id val id: String? = null,
        @JsonProperty("name") @Field val name: String,
        @JsonProperty("clients") var clients: MutableList<Client>? = mutableListOf()
)
