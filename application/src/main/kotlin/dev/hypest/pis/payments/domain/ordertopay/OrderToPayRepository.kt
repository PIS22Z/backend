package dev.hypest.pis.payments.domain.ordertopay

import java.util.UUID

interface OrderToPayRepository {

    fun add(orderToPay: OrderToPay)

    fun save(orderToPay: OrderToPay)

    fun load(id: UUID): OrderToPay?
}
