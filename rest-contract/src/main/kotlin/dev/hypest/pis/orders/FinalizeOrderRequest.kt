package dev.hypest.pis.orders

import java.util.UUID

data class FinalizeOrderRequest(
    val userId: UUID
    // TODO add delivery details
)
