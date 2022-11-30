package dev.hypest.pis.orders.domain.draftorder

import jakarta.inject.Singleton
import java.util.UUID


interface CreateOrderHandler {
    fun create(command: CreateOrderCommand): UUID
}

@Singleton
class CreateOrderHandlerImpl(
    private val draftOrderRepository: DraftOrderRepository
) : CreateOrderHandler {
    override fun create(command: CreateOrderCommand): UUID {
        val draftOrder = DraftOrder.new(
            restaurantId = command.restaurantId,
            userId = command.userId,
            items = command.items
        )
        draftOrderRepository.add(draftOrder)
        return draftOrder.id
    }
}

data class CreateOrderCommand(
    val restaurantId: UUID,
    val userId: UUID,
    val items: List<OrderItem>
)
