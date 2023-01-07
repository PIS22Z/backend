package dev.hypest.pis.restaurants.domain.activeorder

import jakarta.inject.Singleton
import java.util.UUID

interface AcceptActiveOrderHandler {
    fun accept(command: AcceptActiveOrderCommand): UUID
}

@Singleton
class AcceptActiveOrderHandlerImpl(
    private val activeOrderRepository: ActiveOrderRepository
) : AcceptActiveOrderHandler {

    override fun accept(command: AcceptActiveOrderCommand): UUID {
        val order = getOrder(command)
        order.accept()
        activeOrderRepository.save(order)
        return order.id
    }

    private fun getOrder(command: AcceptActiveOrderCommand): ActiveOrder {
        return activeOrderRepository.load(command.orderId)
            ?: throw ActiveOrderNotFoundException(command.orderId)
    }
}

data class AcceptActiveOrderCommand(
    val orderId: UUID
)
