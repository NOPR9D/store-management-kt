package com.boucham.storeManagement.models.actions

import java.time.Instant

// Relation Client > Magasin visité
data class Visited(
        val clientId: String,
        val storeId: String,
        val visited_at: Instant = Instant.now()
)