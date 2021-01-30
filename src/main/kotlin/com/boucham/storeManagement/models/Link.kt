package com.boucham.storeManagement.models;

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

// Model client
@Document(collection = "links")
data class Link(
        @JsonProperty("Id") @Id val id: String? = null,
        @JsonProperty("clientId")  @Field val clientId: String,
        @JsonProperty("storeId")  @Field val storeId: String
)


