package dev.hypest.pis.delivery.domain.orderdelivery

import jakarta.inject.Singleton
import java.util.UUID

interface FinishOrderDeliveryHandler {
    fun finish(command: FinishOrderDeliveryCommand): UUID
}

@Singleton
class FinishOrderDeliveryImpl(
    private val orderDeliveryRepository: OrderDeliveryRepository
) : FinishOrderDeliveryHandler {

    override fun finish(command: FinishOrderDeliveryCommand): UUID {
        val delivery = getOrderDelivery(command)
        delivery.finish()
        orderDeliveryRepository.save(delivery)
        return delivery.id
    }

    private fun getOrderDelivery(command: FinishOrderDeliveryCommand): OrderDelivery {
        return orderDeliveryRepository.load(command.orderDeliveryId)
            ?: throw OrderDeliveryNotFoundException(command.orderDeliveryId)
    }
}

data class FinishOrderDeliveryCommand(
    val orderDeliveryId: UUID
)
