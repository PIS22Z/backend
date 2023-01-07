@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.orders.adapter.`in`.query

import dev.hypest.pis.orders.OrderResponse
import java.util.UUID

interface OrderQuery {

    fun getOrder(orderId: UUID): OrderResponse

    fun getOrders(): List<OrderResponse>
}
