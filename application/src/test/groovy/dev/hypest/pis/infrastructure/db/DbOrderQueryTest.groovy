package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.BaseTest
import dev.hypest.pis.orders.adapter.in.query.OrderQuery
import dev.hypest.pis.orders.infrastructure.db.draftorder.DraftOrderEntity
import dev.hypest.pis.orders.infrastructure.db.draftorder.MicronautDataDraftOrderRepository
import dev.hypest.pis.orders.infrastructure.db.draftorder.OrderItem
import dev.hypest.pis.payments.infrastructure.db.ordertopay.DeliveryDetails
import dev.hypest.pis.payments.infrastructure.db.ordertopay.MicronautDataOrderToPayRepository
import dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderToPayEntity
import jakarta.inject.Inject

class DbOrderQueryTest extends BaseTest {

    @Inject
    private OrderQuery orderQuery

    @Inject
    private MicronautDataDraftOrderRepository draftOrderRepository

    @Inject
    private MicronautDataOrderToPayRepository orderToPayRepository

    def "given existing order, when order is queried, then it should return order"() {
        given:
        def existingOrder = draftOrderRepository.save(
                new DraftOrderEntity(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        [
                                new OrderItem(
                                        UUID.randomUUID(),
                                        1
                                )
                        ],
                        false
                )
        )
        def existingOrderToPay = orderToPayRepository.save(
                new OrderToPayEntity(
                        existingOrder.id,
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        [
                                new dev.hypest.pis.payments.infrastructure.db.ordertopay.OrderItem(
                                        UUID.randomUUID(),
                                        1
                                )
                        ],
                        BigDecimal.ONE,
                        new DeliveryDetails("address"),
                        false
                )
        )

        when:
        def order = orderQuery.getOrder(existingOrder.id)

        then:
        order != null
        order.id == existingOrder.id
        order.userId != null
        order.items.size() == existingOrder.items.size()
        order.items[0].productId == existingOrder.items[0].productId
        order.items[0].quantity == existingOrder.items[0].quantity
        order.amount == existingOrderToPay.amount
        order.deliveryDetails.address == existingOrderToPay.deliveryDetails.address
        !order.isFinalized()
        !order.isPaid()
    }
}
