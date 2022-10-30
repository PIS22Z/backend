package dev.hypest.pis.payments.adapter.in.event

import dev.hypest.pis.BaseTest
import dev.hypest.pis.payments.OrderFinalizedEventTestProvider
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayHandler
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayHandlerImpl
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrderFinalizedEventListenerTest extends BaseTest {

    @Inject
    private OrderFinalizedEventListener listener

    @Inject
    CreateOrderToPayHandler createOrderToPayHandler

    def "given OrderFinalized event, when listener is invoked, then it should create order to pay"() {
        given:
        CreateOrderToPayCommand command
        def event = OrderFinalizedEventTestProvider.getOrderFinalizedEvent()

        when:
        listener.receive(event)

        then:
        1 * createOrderToPayHandler.create(_ as CreateOrderToPayCommand) >> { args ->
            command = args[0] as CreateOrderToPayCommand
            event.aggregateId
        }

        command != null
        command.orderId == event.aggregateId
        command.userId == event.userId
        command.amount == event.amount
    }

    @MockBean(CreateOrderToPayHandlerImpl)
    CreateOrderToPayHandler createOrderToPayHandler() {
        Mock(CreateOrderToPayHandler)
    }
}
