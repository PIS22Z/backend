package dev.hypest.pis.common.eventaggregator

import java.time.Instant
import java.util.UUID

open class DomainEvent(
    val id: UUID = UUID.randomUUID(),
    val aggregateId: UUID,
    val createdAt: Instant = Instant.now()
)
