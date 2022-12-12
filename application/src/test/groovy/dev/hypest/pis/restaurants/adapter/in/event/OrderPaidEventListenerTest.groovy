package dev.hypest.pis.restaurants.adapter.in.event

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.OrderPaidEventTestProvider
import dev.hypest.pis.restaurants.domain.activeorder.CreateActiveOrderCommand
import dev.hypest.pis.restaurants.domain.activeorder.CreateActiveOrderHandler
import dev.hypest.pis.restaurants.domain.activeorder.CreateActiveOrderHandlerImpl
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrderPaidEventListenerTest extends BaseTest {

    @Inject
    private OrderPaidEventListener listener

    @Inject
    CreateActiveOrderHandler createActiveOrderHandler

    def "given OrderPaid event, when listener is invoked, then it should create active order"() {
        given:
        CreateActiveOrderCommand command
        def event = OrderPaidEventTestProvider.getOrderPaidEvent()

        when:
        listener.receive(event)

        then:
        1 * createActiveOrderHandler.create(_ as CreateActiveOrderCommand) >> { args ->
            command = args[0] as CreateActiveOrderCommand
            event.aggregateId
        }

        command != null
        command.orderId == event.aggregateId
        command.userId == event.userId
    }

    @MockBean(CreateActiveOrderHandlerImpl)
    CreateActiveOrderHandler createActiveOrderHandler() {
        Mock(CreateActiveOrderHandler)
    }
}
