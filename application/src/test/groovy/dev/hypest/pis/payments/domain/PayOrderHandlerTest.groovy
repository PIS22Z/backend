package dev.hypest.pis.payments.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.payments.OrderToPayTestProvider
import dev.hypest.pis.payments.domain.ordertopay.OrderToPayRepository
import dev.hypest.pis.payments.domain.ordertopay.PayOrderHandler
import jakarta.inject.Inject

class PayOrderHandlerTest extends BaseTest {

    @Inject
    private PayOrderHandler handler

    @Inject
    private OrderToPayRepository repository

    def "given order to pay, when it is paid, then it should be marked as paid"() {
        given:
        def order = OrderToPayTestProvider.getAggregate()
        repository.add(order)

        def command = OrderToPayTestProvider.getPayOrderCommand(orderId: order.id)

        when:
        handler.pay(command)

        then:
        def savedOrder = repository.load(order.id)
        savedOrder != null
        savedOrder.isPaid()
    }

}
