package dev.hypest.pis.restaurants.domain.activeorder

import java.util.UUID

interface ActiveOrderRepository {

    fun add(activeOrder: ActiveOrder)

    fun save(activeOrder: ActiveOrder)

    fun load(id: UUID): ActiveOrder?
}
