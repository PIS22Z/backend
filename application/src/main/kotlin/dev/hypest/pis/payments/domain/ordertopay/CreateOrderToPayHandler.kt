package dev.hypest.pis.payments.domain.ordertopay

import jakarta.inject.Singleton
import java.math.BigDecimal
import java.util.UUID

interface CreateOrderToPayHandler {
    fun create(command: CreateOrderToPayCommand): UUID
}

@Singleton
class CreateOrderToPayHandlerImpl(
    private val orderToPayRepository: OrderToPayRepository
) : CreateOrderToPayHandler {
    override fun create(command: CreateOrderToPayCommand): UUID {
        val orderToPay = OrderToPay.new(
            userId = command.userId,
            amount = command.amount
        )
        orderToPayRepository.add(orderToPay)
        return orderToPay.id
    }
}

data class CreateOrderToPayCommand(
    val orderId: UUID,
    val userId: UUID,
    val amount: BigDecimal
)
