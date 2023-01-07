package dev.hypest.pis.payments.adapter.in.event

import dev.hypest.pis.BaseTest
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderCommand
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderHandler
import dev.hypest.pis.restaurants.OrderRejectedEvent
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrderRejectedEventListenerTest extends BaseTest {

    @Inject
    private OrderRejectedEventListener listener

    @Inject
    RefundOrderHandler refundOrderHandler

    def "given OrderRejected event, when listener is invoked, then it should refund order"() {
        given:
        def event = new OrderRejectedEvent(UUID.randomUUID())
        RefundOrderCommand command

        when:
        listener.receive(event)

        then:
        1 * refundOrderHandler.refund(_ as RefundOrderCommand) >> { args ->
            command = args[0] as RefundOrderCommand
            command.orderId == event.orderId
            event.aggregateId
        }

        command != null
        command.orderId == event.orderId
    }

    @MockBean(RefundOrderHandler)
    RefundOrderHandler refundOrderHandler() {
        Mock(RefundOrderHandler)
    }
}
