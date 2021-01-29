package com.boucham.storeManagement.models.actions

import java.time.Instant

data class Visited(
        val clientId: String,
        val storeId: String,
        val visited_at: Instant?
)