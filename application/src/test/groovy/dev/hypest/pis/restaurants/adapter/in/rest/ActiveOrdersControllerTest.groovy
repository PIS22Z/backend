package dev.hypest.pis.restaurants.adapter.in.rest

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.domain.activeorder.*
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class ActiveOrdersControllerTest extends BaseTest {

    @Inject
    MarkOrderAsReadyToDeliverHandler markOrderAsReadyToDeliverHandler

    @Inject
    AcceptActiveOrderHandler acceptActiveOrderHandler

    @Inject
    RejectActiveOrderHandler rejectActiveOrderHandler

    @Inject
    private ActiveOrdersClient client

    def "when put is performed against /orders/{orderId}/ready"() {
        given:
        MarkOrderAsReadyToDeliverCommand command
        UUID orderId = UUID.randomUUID()

        when:
        def response = client.markOrderAsReadyToDeliver(orderId)

        then:
        1 * markOrderAsReadyToDeliverHandler.markAsReady(_ as MarkOrderAsReadyToDeliverCommand) >> { args ->
            command = args[0] as MarkOrderAsReadyToDeliverCommand
            command.orderId == orderId
            orderId
        }

        command != null
        command.orderId == orderId

        response.status == HttpStatus.OK
        response.body().id == orderId
    }

    def "when put is performed against /orders/{orderId}/accept"() {
        given:
        AcceptActiveOrderCommand command
        UUID orderId = UUID.randomUUID()

        when:
        def response = client.acceptActiveOrder(orderId)

        then:
        1 * acceptActiveOrderHandler.accept(_ as AcceptActiveOrderCommand) >> { args ->
            command = args[0] as AcceptActiveOrderCommand
            command.orderId == orderId
            orderId
        }

        command != null
        command.orderId == orderId

        response.status == HttpStatus.OK
        response.body().id == orderId
    }

    def "when put is performed against /orders/{orderId}/reject"() {
        given:
        RejectActiveOrderCommand command
        UUID orderId = UUID.randomUUID()

        when:
        def response = client.rejectActiveOrder(orderId)

        then:
        1 * rejectActiveOrderHandler.reject(_ as RejectActiveOrderCommand) >> { args ->
            command = args[0] as RejectActiveOrderCommand
            command.orderId == orderId
            orderId
        }

        command.orderId != null
        command.orderId == orderId

        response.status == HttpStatus.OK
        response.body().id == orderId
    }

    @MockBean(MarkOrderAsReadyToDeliverHandler)
    MarkOrderAsReadyToDeliverHandler markOrderAsReadyToDeliverHandler() {
        Mock(MarkOrderAsReadyToDeliverHandler)
    }

    @MockBean(AcceptActiveOrderHandler)
    AcceptActiveOrderHandler acceptActiveOrderHandler() {
        Mock(AcceptActiveOrderHandler)
    }

    @MockBean(RejectActiveOrderHandler)
    RejectActiveOrderHandler rejectActiveOrderHandler() {
        Mock(RejectActiveOrderHandler)
    }
}
