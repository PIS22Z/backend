package dev.hypest.pis.orders.adapter.in.rest

import dev.hypest.pis.BaseTest
import dev.hypest.pis.orders.CreateOrderRequest
import dev.hypest.pis.orders.FinalizeOrderRequest
import dev.hypest.pis.orders.OrderItem
import dev.hypest.pis.orders.domain.draftorder.CreateOrderCommand
import dev.hypest.pis.orders.domain.draftorder.CreateOrderHandler
import dev.hypest.pis.orders.domain.draftorder.CreateOrderHandlerImpl
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderCommand
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderHandler
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderHandlerImpl
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrdersControllerTest extends BaseTest {

    @Inject
    CreateOrderHandler createOrderHandler

    @Inject
    FinalizeOrderHandler finalizeOrderHandler

    @Inject
    private OrdersClient client

    def "when post is performed against /orders"() {
        given:
        CreateOrderCommand command

        when:
        def request = new CreateOrderRequest(UUID.randomUUID(), [new OrderItem(UUID.randomUUID(), 1)])
        def response = client.createOrder(request)

        then:
        1 * createOrderHandler.create(_ as CreateOrderCommand) >> { args ->
            command = args[0] as CreateOrderCommand
            UUID.randomUUID()
        }

        command != null
        command.userId == request.userId

        response.status == HttpStatus.CREATED
        response.body().id != null
    }

    def "when put is performed against /orders/{orderId}"() {
        given:
        FinalizeOrderCommand command
        def orderId = UUID.randomUUID()

        when:
        def request = new FinalizeOrderRequest(UUID.randomUUID())
        def response = client.finalizeOrder(orderId, request)

        then:
        1 * finalizeOrderHandler.finalize(_ as FinalizeOrderCommand) >> { args ->
            command = args[0] as FinalizeOrderCommand
            orderId
        }

        command != null
        command.userId == request.userId
        command.orderId == orderId

        response.status == HttpStatus.OK
        response.body().id == orderId
    }

    @MockBean(CreateOrderHandlerImpl)
    CreateOrderHandler createOrderHandler() {
        Mock(CreateOrderHandler)
    }

    @MockBean(FinalizeOrderHandlerImpl)
    FinalizeOrderHandler finalizeOrderHandler() {
        Mock(FinalizeOrderHandler)
    }
}
