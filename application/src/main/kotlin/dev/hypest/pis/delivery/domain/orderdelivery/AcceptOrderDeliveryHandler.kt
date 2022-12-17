package dev.hypest.pis.delivery.domain.orderdelivery

import jakarta.inject.Singleton
import java.util.UUID

interface AcceptOrderDeliveryHandler {
    fun accept(command: AcceptOrderDeliveryCommand): UUID
}

@Singleton
class AcceptOrderDeliveryHandlerImpl(
    private val orderDeliveryRepository: OrderDeliveryRepository
) : AcceptOrderDeliveryHandler {

    override fun accept(command: AcceptOrderDeliveryCommand): UUID {
        val delivery = getOrderDelivery(command)
        delivery.accept(command.courierId)
        orderDeliveryRepository.save(delivery)
        return delivery.id
    }

    private fun getOrderDelivery(command: AcceptOrderDeliveryCommand): OrderDelivery {
        return orderDeliveryRepository.load(command.orderDeliveryId)
            ?: throw OrderDeliveryNotFoundException(command.orderDeliveryId)
    }
}

data class AcceptOrderDeliveryCommand(
    val orderDeliveryId: UUID,
    val courierId: UUID
)
