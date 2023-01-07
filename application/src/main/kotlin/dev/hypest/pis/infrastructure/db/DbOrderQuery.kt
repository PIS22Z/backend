package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.MicronautDataOrderDeliveryRepository
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.OrderDeliveryEntity
import dev.hypest.pis.orders.OrderResponse
import dev.hypest.pis.orders.adapter.`in`.query.OrderQuery
import dev.hypest.pis.orders.domain.draftorder.DraftOrderNotFoundException
import dev.hypest.pis.orders.infrastructure.db.draftorder.DraftOrderEntity
import dev.hypest.pis.orders.infrastructure.db.draftorder.MicronautDataDraftOrderRepository
import dev.hypest.pis.payments.infrastructure.db.ordertopay.MicronautDataOrderToPayRepository
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderToPayEntity
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.ActiveOrderEntity
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.MicronautDataActiveOrderRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbOrderQuery(
    private val draftOrderRepository: MicronautDataDraftOrderRepository,
    private val orderToPayRepository: MicronautDataOrderToPayRepository,
    private val activeOrderRepository: MicronautDataActiveOrderRepository,
    private val orderDeliveryRepository: MicronautDataOrderDeliveryRepository
) : OrderQuery {

    override fun getOrder(orderId: UUID): OrderResponse {
        val draftOrder =
            draftOrderRepository.findById(orderId).unwrap()
                ?: throw DraftOrderNotFoundException(orderId)
        val orderToPay = orderToPayRepository.findById(orderId).unwrap()
        val activeOrder = activeOrderRepository.findById(orderId).unwrap()
        val orderDelivery = orderDeliveryRepository.findById(orderId).unwrap()

        return OrderResponse(
            id = orderId,
            restaurantId = draftOrder.restaurantId,
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
            status = getStatus(draftOrder, orderToPay, activeOrder, orderDelivery)
        )
    }

    override fun getOrders(): List<OrderResponse> {
        val draftOrders = draftOrderRepository.findAll()
        return draftOrders.map { getOrder(it.id) }
    }

    private fun getStatus(
        draftOrder: DraftOrderEntity,
        orderToPay: OrderToPayEntity?,
        activeOrder: ActiveOrderEntity?,
        orderDelivery: OrderDeliveryEntity?
    ) = when {
//        orderDelivery?.isDelivered -> OrderResponse.OrderStatus.DELIVERED
        orderDelivery?.isBeingDelivered == true -> OrderResponse.OrderStatus.DELIVERY_IN_PROGRESS
        orderDelivery?.assignedCourierId != null -> OrderResponse.OrderStatus.COURIER_ASSIGNED
        activeOrder?.isReadyToDeliver == true -> OrderResponse.OrderStatus.READY_TO_DELIVER
        activeOrder?.isAccepted == true -> OrderResponse.OrderStatus.ACCEPTED
        activeOrder?.isAccepted == false -> OrderResponse.OrderStatus.REJECTED
        orderToPay?.isPaid == true -> OrderResponse.OrderStatus.PAID
        draftOrder.isFinalized -> OrderResponse.OrderStatus.FINALIZED
        else -> OrderResponse.OrderStatus.CREATED
    }

}
