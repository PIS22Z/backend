package dev.hypest.pis.delivery.domain.orderdelivery

import jakarta.inject.Singleton
import java.util.UUID

interface StartOrderDeliveryHandler {
    fun start(command: StartOrderDeliveryCommand): UUID
}

@Singleton
class StartOrderDeliveryHandlerImpl(
    private val orderDeliveryRepository: OrderDeliveryRepository
) : StartOrderDeliveryHandler {

    override fun start(command: StartOrderDeliveryCommand): UUID {
        val delivery = getOrderDelivery(command)
        delivery.start()
        orderDeliveryRepository.save(delivery)
        return delivery.id
    }

    private fun getOrderDelivery(command: StartOrderDeliveryCommand): OrderDelivery {
        return orderDeliveryRepository.load(command.orderDeliveryId)
            ?: throw OrderDeliveryNotFoundException(command.orderDeliveryId)
    }
}

data class StartOrderDeliveryCommand(
    val orderDeliveryId: UUID
)
