package dev.hypest.pis.payments.domain.ordertopay

import jakarta.inject.Singleton
import java.util.UUID

interface RefundOrderHandler {
    fun refund(command: RefundOrderCommand): UUID
}

@Singleton
class RefundOrderHandlerImpl(
    private val orderToPayRepository: OrderToPayRepository
) : RefundOrderHandler {

    override fun refund(command: RefundOrderCommand): UUID {
        val order = orderToPayRepository.load(command.orderId)
            ?: throw OrderToPayNotFoundException(command.orderId)
        order.refund()
        orderToPayRepository.save(order)
        return order.id
    }
}

data class RefundOrderCommand(
    val orderId: UUID
)
