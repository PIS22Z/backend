package dev.hypest.pis.orders.domain.draftorder

import jakarta.inject.Singleton
import java.math.BigDecimal
import java.util.UUID

interface FinalizeOrderHandler {
    fun finalize(command: FinalizeOrderCommand): UUID
}

@Singleton
class FinalizeOrderHandlerImpl(
    private val draftOrderRepository: DraftOrderRepository
) : FinalizeOrderHandler {

    override fun finalize(command: FinalizeOrderCommand): UUID {
        val draftOrder = getOrder(command)

        // TODO verify that the order is not already finalized
        // TODO verify that all products are available (REST call to restaurants)
        // TODO get order amount (REST call to restaurants)
        val orderAmount = BigDecimal("16.99")

        draftOrder.finalize(orderAmount)
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
    val userId: UUID
)
