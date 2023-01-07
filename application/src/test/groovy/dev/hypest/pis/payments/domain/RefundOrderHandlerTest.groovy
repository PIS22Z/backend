package dev.hypest.pis.payments.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.payments.OrderToPayTestProvider
import dev.hypest.pis.payments.domain.ordertopay.OrderToPayRepository
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderCommand
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderHandler
import jakarta.inject.Inject

class RefundOrderHandlerTest extends BaseTest {

    @Inject
    private RefundOrderHandler refundOrderHandler

    @Inject
    private OrderToPayRepository repository

    def "given order to pay, when it is refunded, then it should be marked as refunded"() {
        given:
        def order = OrderToPayTestProvider.getAggregate(isPaid: true)
        def command = new RefundOrderCommand(order.id)

        repository.add(order)

        when:
        refundOrderHandler.refund(command)

        then:
        def savedOrder = repository.load(order.id)
        savedOrder != null
        savedOrder.isRefunded()
        !savedOrder.isPaid()
    }
}
