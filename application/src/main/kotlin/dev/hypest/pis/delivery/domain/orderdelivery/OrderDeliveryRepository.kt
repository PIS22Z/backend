package dev.hypest.pis.delivery.domain.orderdelivery

import java.util.UUID

interface OrderDeliveryRepository {

    fun add(orderDelivery: OrderDelivery)

    fun save(orderDelivery: OrderDelivery)

    fun load(id: UUID): OrderDelivery?
}
