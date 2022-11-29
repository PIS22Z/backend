package dev.hypest.pis.orders.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.orders.DraftOrderTestProvider
import dev.hypest.pis.orders.domain.draftorder.CreateOrderHandler
import dev.hypest.pis.orders.domain.draftorder.DraftOrderRepository
import jakarta.inject.Inject

class CreateOrderHandlerTest extends BaseTest {

    @Inject
    private CreateOrderHandler handler

    @Inject
    private DraftOrderRepository orderRepository

    def "given nothing, when valid order is created, then it should be saved in db"() {
        given:
        def command = DraftOrderTestProvider.getCreateOrderCommand()

        when:
        def id = handler.create(command)

        then:
        def savedOrder = orderRepository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.userId == command.userId
        !savedOrder.isFinalized()
    }
}
