package dev.hypest.pis.payments.domain.ordertopay

import jakarta.inject.Singleton
import java.util.UUID

interface PayOrderHandler {
    fun pay(command: PayOrderCommand): UUID
}

@Singleton
class PayOrderHandlerImpl(
    private val orderToPayRepository: OrderToPayRepository
) : PayOrderHandler {

    override fun pay(command: PayOrderCommand): UUID {
        val order = orderToPayRepository.load(command.orderId)
            ?: throw OrderToPayNotFoundException(command.orderId)
        order.pay()
        orderToPayRepository.save(order)
        return order.id
    }
}

data class PayOrderCommand(
    val orderId: UUID
)
