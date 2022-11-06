package dev.hypest.pis.orders.domain.draftorder

import java.util.UUID

interface DraftOrderRepository {

    fun add(draftOrder: DraftOrder)

    fun save(draftOrder: DraftOrder)

    fun load(id: UUID): DraftOrder?
}
