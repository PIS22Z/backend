package dev.hypest.pis.restaurants.adapter.in.rest

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverCommand
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverHandler
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class ActiveOrdersControllerTest extends BaseTest {

    @Inject
    MarkOrderAsReadyToDeliverHandler markOrderAsReadyToDeliverHandler

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
        }

        command != null
        command.orderId == orderId

        response.status == HttpStatus.OK
        response.body().id == orderId
    }

    @MockBean(MarkOrderAsReadyToDeliverHandler)
    MarkOrderAsReadyToDeliverHandler markOrderAsReadyToDeliverHandler() {
        Mock(MarkOrderAsReadyToDeliverHandler)
    }

}
