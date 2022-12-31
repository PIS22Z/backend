package dev.hypest.pis.delivery.adapter.in.event

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.OrderReadyToDeliverEventTestProvider
import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryHandler
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrderReadyToDeliverEventListenerTest extends BaseTest {

    @Inject
    private OrderReadyToDeliverEventListener listener

    @Inject
    CreateOrderDeliveryHandler createOrderDeliveryHandler

    def "given OrderReadyToDeliver event, when listener is invoked, then it should create order delivery"() {
        given:
        CreateOrderDeliveryCommand command
        def event = OrderReadyToDeliverEventTestProvider.getOrderReadyToDeliverEvent()

        when:
        listener.receive(event)

        then:
        1 * createOrderDeliveryHandler.create(_ as CreateOrderDeliveryCommand) >> { args ->
            command = args[0] as CreateOrderDeliveryCommand
            event.aggregateId
        }

        command != null
        command.orderId == event.aggregateId
        command.restaurantId == event.restaurantId
        command.deliveryDetails.address == event.deliveryDetails.address
    }

    @MockBean(CreateOrderDeliveryHandler)
    CreateOrderDeliveryHandler createOrderDeliveryHandler() {
        Mock(CreateOrderDeliveryHandler)
    }
}
