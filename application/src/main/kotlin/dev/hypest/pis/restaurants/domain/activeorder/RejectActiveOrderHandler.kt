package dev.hypest.pis.restaurants.domain.activeorder

import jakarta.inject.Singleton
import java.util.UUID

interface RejectActiveOrderHandler {
    fun reject(command: RejectActiveOrderCommand): UUID
}

@Singleton
class RejectActiveOrderHandlerImpl(
    private val activeOrderRepository: ActiveOrderRepository
) : RejectActiveOrderHandler {

    override fun reject(command: RejectActiveOrderCommand): UUID {
        val order = getOrder(command)
        order.reject()
        activeOrderRepository.save(order)
        return order.id
    }

    private fun getOrder(command: RejectActiveOrderCommand): ActiveOrder {
        return activeOrderRepository.load(command.orderId)
            ?: throw ActiveOrderNotFoundException(command.orderId)
    }
}

data class RejectActiveOrderCommand(
    val orderId: UUID
)
