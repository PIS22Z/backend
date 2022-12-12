package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.orders.OrderResponse
import dev.hypest.pis.orders.adapter.`in`.query.OrderQuery
import dev.hypest.pis.orders.domain.draftorder.DraftOrderNotFoundException
import dev.hypest.pis.orders.infrastructure.db.draftorder.MicronautDataDraftOrderRepository
import dev.hypest.pis.payments.infrastructure.db.ordertopay.MicronautDataOrderToPayRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbOrderQuery(
    private val draftOrderRepository: MicronautDataDraftOrderRepository,
    private val orderToPayRepository: MicronautDataOrderToPayRepository
) : OrderQuery {

    override fun getOrder(orderId: UUID): OrderResponse {
        val draftOrder =
            draftOrderRepository.findById(orderId).unwrap()
                ?: throw DraftOrderNotFoundException(orderId)
        val orderToPay = orderToPayRepository.findById(orderId).unwrap()

        return OrderResponse(
            id = orderId,
            userId = draftOrder.userId,
            items = draftOrder.items.map {
                OrderResponse.OrderItem(
                    productId = it.productId,
                    quantity = it.quantity
                )
            },
            amount = orderToPay?.amount,
            deliveryDetails = orderToPay?.let {
                OrderResponse.DeliveryDetails(
                    address = it.deliveryDetails.address
                )
            },
            isFinalized = draftOrder.isFinalized,
            isPaid = orderToPay?.isPaid ?: false
        )
    }
}
