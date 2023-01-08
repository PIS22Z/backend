package dev.hypest.pis.delivery.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.OrderDeliveryTestProvider
import dev.hypest.pis.delivery.domain.orderdelivery.FinishOrderDeliveryHandler
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryNotFoundException
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryRepository
import jakarta.inject.Inject

class FinishOrderDeliveryHandlerTest extends BaseTest {

    @Inject
    private FinishOrderDeliveryHandler handler

    @Inject
    private OrderDeliveryRepository repository

    def "given existing delivery, when finishing it, then it should be finished & saved in db"() {
        given:
        def orderDelivery = OrderDeliveryTestProvider.getAggregate()
        orderDelivery.assignedCourierId = UUID.randomUUID()
        orderDelivery.isBeingDelivered = true
        repository.add(orderDelivery)

        def command = OrderDeliveryTestProvider.getFinishOrderDeliveryCommand(orderDeliveryId: orderDelivery.id)

        when:
        def id = handler.finish(command)

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.isDelivered
    }

    def "given not existing delivery, when finishing it, it should throw"() {
        given:
        def command = OrderDeliveryTestProvider.getFinishOrderDeliveryCommand()

        when:
        handler.finish(command)

        then:
        def e = thrown(OrderDeliveryNotFoundException)
        e.message == "Order delivery with id ${command.orderDeliveryId} not found"
    }
}
