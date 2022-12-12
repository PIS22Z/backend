package dev.hypest.pis.restaurants.domain.activeorder

import jakarta.inject.Singleton
import java.util.UUID

interface CreateActiveOrderHandler {
    fun create(command: CreateActiveOrderCommand): UUID
}

@Singleton
class CreateActiveOrderHandlerImpl(
    private val activeOrderRepository: ActiveOrderRepository
) : CreateActiveOrderHandler {

    override fun create(command: CreateActiveOrderCommand): UUID {
        val activeOrder = ActiveOrder.new(
            id = command.orderId,
            restaurantId = command.restaurantId,
            userId = command.userId,
            items = command.items
        )
        activeOrderRepository.add(activeOrder)
        return activeOrder.id
    }
}

data class CreateActiveOrderCommand(
    val orderId: UUID,
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>
)
