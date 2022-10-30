package dev.hypest.pis.payments.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.payments.OrderToPayTestProvider
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayHandler
import dev.hypest.pis.payments.domain.ordertopay.OrderToPayRepository
import jakarta.inject.Inject

class CreateOrderToPayHandlerTest extends BaseTest {

    @Inject
    private CreateOrderToPayHandler handler

    @Inject
    private OrderToPayRepository repository

    def "given nothing, when valid order to pay is created, then it should be saved in db"() {
        given:
        def command = OrderToPayTestProvider.getCreateOrderToPayCommand()

        when:
        def id = handler.create(command)

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.userId == command.userId
        savedOrder.amount == command.amount
        !savedOrder.isPaid()
    }
}
