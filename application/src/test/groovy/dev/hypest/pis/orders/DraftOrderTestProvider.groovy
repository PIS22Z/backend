package dev.hypest.pis.orders

import dev.hypest.pis.orders.domain.draftorder.CreateOrderCommand
import dev.hypest.pis.orders.domain.draftorder.DraftOrder
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderCommand
import dev.hypest.pis.orders.domain.draftorder.OrderItem
import java.util.UUID

class DraftOrderTestProvider {

    static CreateOrderCommand getCreateOrderCommand() {
        return new CreateOrderCommand(UUID.randomUUID(),
                [new OrderItem(UUID.randomUUID(),
                        1)])
    }

    static FinalizeOrderCommand getFinalizeOrderCommand(UUID orderId) {
        return new FinalizeOrderCommand(orderId, UUID.randomUUID())
    }

    static DraftOrder getAggregate() {
        return DraftOrder.new(UUID.randomUUID(),
                [new OrderItem(UUID.randomUUID(),
                        1)])
    }
}
