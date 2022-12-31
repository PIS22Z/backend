package dev.hypest.pis.restaurants.domain.activeorder

import jakarta.inject.Singleton
import java.util.UUID

interface MarkOrderAsReadyToDeliverHandler {
    fun markAsReady(command: MarkOrderAsReadyToDeliverCommand): UUID
}

@Singleton
class MarkOrderAsReadyToDeliverHandlerImpl(
    private val activeOrderRepository: ActiveOrderRepository
) : MarkOrderAsReadyToDeliverHandler {

    override fun markAsReady(command: MarkOrderAsReadyToDeliverCommand): UUID {
        val order = getOrder(command)
        order.markAsReadyToDeliver()
        activeOrderRepository.save(order)
        return order.id
    }

    private fun getOrder(command: MarkOrderAsReadyToDeliverCommand): ActiveOrder {
        return activeOrderRepository.load(command.orderId)
            ?: throw ActiveOrderNotFoundException(command.orderId)
    }
}

data class MarkOrderAsReadyToDeliverCommand(
    val orderId: UUID
)
