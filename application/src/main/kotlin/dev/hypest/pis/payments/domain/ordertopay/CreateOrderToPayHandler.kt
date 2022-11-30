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
            id=command.orderId,
            restaurantId = command.restaurantId,
            userId = command.userId,
            items = command.items,
            amount = command.amount,
            deliveryDetails = command.deliveryDetails
        )
        orderToPayRepository.add(orderToPay)
        return orderToPay.id
    }
}

data class CreateOrderToPayCommand(
    val orderId: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>,
    val amount: BigDecimal,
    val deliveryDetails: DeliveryDetails
)
