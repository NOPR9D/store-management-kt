package com.boucham.storeManagement.models;

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

// Model client
@Document(collection = "clients")
data class Client(
        @JsonProperty("Id") @Id val id: String? = null,
        @JsonProperty("firstName") @Field val firstName: String,
        @JsonProperty("lastName") @Field val lastName: String,
        @JsonProperty("linkWith") @Field val linkWith: MutableList<String>? = mutableListOf()
)


