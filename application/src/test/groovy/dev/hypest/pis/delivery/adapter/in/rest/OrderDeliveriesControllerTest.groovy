package dev.hypest.pis.delivery.adapter.in.rest

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.AcceptOrderDeliveryRequest
import dev.hypest.pis.delivery.OrderDeliveryOfferResponse
import dev.hypest.pis.delivery.adapter.in.query.OrderDeliveryOfferQuery
import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryHandler
import dev.hypest.pis.delivery.domain.orderdelivery.FinishOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.FinishOrderDeliveryHandler
import dev.hypest.pis.delivery.domain.orderdelivery.StartOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.StartOrderDeliveryHandler
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class OrderDeliveriesControllerTest extends BaseTest {

    @Inject
    OrderDeliveryOfferQuery orderDeliveryOfferQuery

    @Inject
    AcceptOrderDeliveryHandler acceptOrderDeliveryHandler

    @Inject
    StartOrderDeliveryHandler startOrderDeliveryHandler

    @Inject
    FinishOrderDeliveryHandler finishOrderDeliveryHandler

    @Inject
    private OrderDeliveriesClient client

    def "when put is performed against /{orderDeliveryId}/accept"() {
        given:
        AcceptOrderDeliveryCommand command
        def deliveryId = UUID.randomUUID()

        when:
        def request = new AcceptOrderDeliveryRequest(UUID.randomUUID())
        def response = client.acceptOrderDelivery(deliveryId, request)

        then:
        1 * acceptOrderDeliveryHandler.accept(_ as AcceptOrderDeliveryCommand) >> { args ->
            command = args[0] as AcceptOrderDeliveryCommand
            deliveryId
        }

        command != null
        command.courierId == request.courierId

        response.status == HttpStatus.OK
        response.body().id != null
    }

    def "when get is performed against /offer"() {
        given:
        def courierAddress = "address"
        def offer = new OrderDeliveryOfferResponse(UUID.randomUUID(), UUID.randomUUID(), new OrderDeliveryOfferResponse.DeliveryDetails("address"))

        when:
        def response = client.getOrderDeliveryOffer(courierAddress)

        then:
        1 * orderDeliveryOfferQuery.getOrderDeliveryOffer(courierAddress) >> offer

        response.status == HttpStatus.OK
        response.body() == offer
    }

    def "when put is performed against /{orderDeliveryId}/start"() {
        given:
        StartOrderDeliveryCommand command
        def deliveryId = UUID.randomUUID()

        when:
        def response = client.startOrderDelivery(deliveryId)

        then:
        1 * startOrderDeliveryHandler.start(_ as StartOrderDeliveryCommand) >> { args ->
            command = args[0] as StartOrderDeliveryCommand
            deliveryId
        }

        command != null
        command.orderDeliveryId == deliveryId

        response.status == HttpStatus.OK
        response.body().id != null
    }

    def "when put is performed against /{orderDeliveryId}/finish"() {
        given:
        FinishOrderDeliveryCommand command
        def deliveryId = UUID.randomUUID()

        when:
        def response = client.finishOrderDelivery(deliveryId)

        then:
        1 * finishOrderDeliveryHandler.start(_ as FinishOrderDeliveryCommand) >> { args ->
            command = args[0] as FinishOrderDeliveryCommand
            deliveryId
        }

        command != null
        command.orderDeliveryId == deliveryId

        response.status == HttpStatus.OK
        response.body().id != null
    }

    @MockBean(OrderDeliveryOfferQuery)
    OrderDeliveryOfferQuery orderDeliveryOfferQuery() {
        Mock(OrderDeliveryOfferQuery)
    }

    @MockBean(AcceptOrderDeliveryHandler)
    AcceptOrderDeliveryHandler acceptOrderDeliveryHandler() {
        Mock(AcceptOrderDeliveryHandler)
    }

    @MockBean(StartOrderDeliveryHandler)
    StartOrderDeliveryHandler startOrderDeliveryHandler() {
        Mock(StartOrderDeliveryHandler)
    }

    @MockBean(FinishOrderDeliveryHandler)
    FinishOrderDeliveryHandler finishOrderDeliveryHandler() {
        Mock(FinishOrderDeliveryHandler)
    }
}
