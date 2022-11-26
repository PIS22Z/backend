package dev.hypest.pis.orders.domain.draftorder

import jakarta.inject.Singleton
import java.util.UUID

interface RemoveItemFromOrderHandler {
    fun removeItem(command: ModifyOrderItemCommand): UUID
}

@Singleton
class RemoveItemFromOrderHandlerImpl(
    private val draftOrderRepository: DraftOrderRepository
) : RemoveItemFromOrderHandler {

    override fun removeItem(command: ModifyOrderItemCommand): UUID {
        val draftOrder = getOrder(command)
        draftOrder.removeItem(
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
