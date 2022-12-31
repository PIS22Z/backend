package dev.hypest.pis.delivery.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.OrderDeliveryTestProvider
import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryHandler
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryNotFoundException
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryRepository
import jakarta.inject.Inject

class AcceptOrderDeliveryHandlerTest extends BaseTest {

    @Inject
    private AcceptOrderDeliveryHandler handler

    @Inject
    private OrderDeliveryRepository repository

    def "given existing delivery, when accepting it, then it should be accepted & saved in db"() {
        given:
        def orderDelivery = OrderDeliveryTestProvider.getAggregate()
        repository.add(orderDelivery)

        def command = OrderDeliveryTestProvider.getAcceptOrderDeliveryCommand(orderDeliveryId: orderDelivery.id)

        when:
        def id = handler.accept(command)

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.assignedCourierId == command.courierId
    }

    def "given not existing delivery, when accepting it, it should throw"() {
        given:
        def command = OrderDeliveryTestProvider.getAcceptOrderDeliveryCommand()

        when:
        def id = handler.accept(command)

        then:
        def e = thrown(OrderDeliveryNotFoundException)
        e.message == "Order delivery with id ${command.orderDeliveryId} not found"
    }
}
