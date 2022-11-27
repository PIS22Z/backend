package dev.hypest.pis.orders.domain.draftorder

import jakarta.inject.Singleton
import java.util.UUID

interface AddItemToOrderHandler {
    fun addItem(command: ModifyOrderItemCommand): UUID
}

@Singleton
class AddItemToOrderHandlerImpl(
    private val draftOrderRepository: DraftOrderRepository
) : AddItemToOrderHandler {
    override fun addItem(command: ModifyOrderItemCommand): UUID {
        val draftOrder = getOrder(command)
        draftOrder.addItem(
            productId = command.product.productId,
            quantity = command.product.quantity
        )
        draftOrderRepository.save(draftOrder)
        return draftOrder.id
    }

    private fun getOrder(command: ModifyOrderItemCommand): DraftOrder {
        return draftOrderRepository.load(command.orderId)
            ?: throw DraftOrderNotFoundException(command.orderId)
    }
}
