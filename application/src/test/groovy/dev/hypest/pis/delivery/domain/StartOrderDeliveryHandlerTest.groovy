package dev.hypest.pis.delivery.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.OrderDeliveryTestProvider
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryNotFoundException
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryRepository
import dev.hypest.pis.delivery.domain.orderdelivery.StartOrderDeliveryHandler
import jakarta.inject.Inject

class StartOrderDeliveryHandlerTest extends BaseTest {

    @Inject
    private StartOrderDeliveryHandler handler

    @Inject
    private OrderDeliveryRepository repository

    def "given existing delivery, when starting it, then it should be started & saved in db"() {
        given:
        def orderDelivery = OrderDeliveryTestProvider.getAggregate()
        orderDelivery.assignedCourierId = UUID.randomUUID()
        repository.add(orderDelivery)

        def command = OrderDeliveryTestProvider.getStartOrderDeliveryCommand(orderDeliveryId: orderDelivery.id)

        when:
        def id = handler.start(command)

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.isBeingDelivered
    }

    def "given not existing delivery, when starting it, it should throw"() {
        given:
        def command = OrderDeliveryTestProvider.getStartOrderDeliveryCommand()

        when:
        def id = handler.start(command)

        then:
        def e = thrown(OrderDeliveryNotFoundException)
        e.message == "Order delivery with id ${command.orderDeliveryId} not found"
    }
}
