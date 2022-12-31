package dev.hypest.pis.delivery.domain.orderdelivery

import jakarta.inject.Singleton
import java.util.UUID

interface CreateOrderDeliveryHandler {
    fun create(command: CreateOrderDeliveryCommand): UUID
}

@Singleton
class CreateOrderDeliveryHandlerImpl(
    private val orderDeliveryRepository: OrderDeliveryRepository
) : CreateOrderDeliveryHandler {

    override fun create(command: CreateOrderDeliveryCommand): UUID {
        val orderDelivery = OrderDelivery.new(
            id = command.orderId,
            restaurantId = command.restaurantId,
            deliveryDetails = command.deliveryDetails
        )
        orderDeliveryRepository.add(orderDelivery)
        return orderDelivery.id
    }
}

data class CreateOrderDeliveryCommand(
    val orderId: UUID,
    val restaurantId: UUID,
    val deliveryDetails: DeliveryDetails
)
