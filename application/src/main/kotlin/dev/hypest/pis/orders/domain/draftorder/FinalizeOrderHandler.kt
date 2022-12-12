package dev.hypest.pis.orders.domain.draftorder

import dev.hypest.pis.orders.domain.restaurants.OrdersToRestaurantsPort
import jakarta.inject.Singleton
import java.util.UUID

interface FinalizeOrderHandler {
    fun finalize(command: FinalizeOrderCommand): UUID
}

@Singleton
class FinalizeOrderHandlerImpl(
    private val draftOrderRepository: DraftOrderRepository,
    private val restaurantsPort: OrdersToRestaurantsPort
) : FinalizeOrderHandler {

    override fun finalize(command: FinalizeOrderCommand): UUID {
        val draftOrder = getOrder(command)
        draftOrder.finalize(command, restaurantsPort)
        draftOrderRepository.save(draftOrder)
        return draftOrder.id
    }

    private fun getOrder(command: FinalizeOrderCommand): DraftOrder {
        return draftOrderRepository.load(command.orderId)
            ?: throw DraftOrderNotFoundException(command.orderId)
    }
}

data class FinalizeOrderCommand(
    val orderId: UUID,
    val userId: UUID,
    val deliveryDetails: DeliveryDetails
)
