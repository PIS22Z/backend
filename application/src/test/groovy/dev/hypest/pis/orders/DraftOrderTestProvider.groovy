package dev.hypest.pis.orders

import dev.hypest.pis.orders.domain.draftorder.CreateOrderCommand
import dev.hypest.pis.orders.domain.draftorder.DeliveryDetails
import dev.hypest.pis.orders.domain.draftorder.DraftOrder
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderCommand
import dev.hypest.pis.orders.domain.draftorder.ModifyOrderItemCommand
import dev.hypest.pis.orders.domain.draftorder.OrderItem
import dev.hypest.pis.orders.domain.restaurants.Product

class DraftOrderTestProvider {

    static CreateOrderCommand getCreateOrderCommand() {
        return new CreateOrderCommand(UUID.randomUUID(), UUID.randomUUID(),
                [new OrderItem(UUID.randomUUID(),
                        1)])
    }

    static FinalizeOrderCommand getFinalizeOrderCommand(UUID orderId) {
        return new FinalizeOrderCommand(orderId, UUID.randomUUID(), new DeliveryDetails("address"))
    }

    static ModifyOrderItemCommand getModifyOrderItemCommand(UUID orderId, UUID productId, Integer quantity) {
        return new ModifyOrderItemCommand(orderId, new OrderItem(productId, quantity))
    }

    static DraftOrder getAggregate(Map map = [:]) {
        return DraftOrder.new(UUID.randomUUID(), UUID.randomUUID(),
                [new OrderItem(map.productId ?: UUID.randomUUID(),
                        1)])
    }

    static Product getProduct(Map map = [:]) {
        return new Product(
                map.id ?: UUID.randomUUID(),
                map.price ?: 1.0
        )
    }
}
