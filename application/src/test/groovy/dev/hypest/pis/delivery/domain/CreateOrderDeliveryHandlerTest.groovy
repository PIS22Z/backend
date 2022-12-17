package dev.hypest.pis.delivery.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.OrderDeliveryTestProvider
import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryHandler
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryRepository
import jakarta.inject.Inject

class CreateOrderDeliveryHandlerTest extends BaseTest {

    @Inject
    private CreateOrderDeliveryHandler handler

    @Inject
    private OrderDeliveryRepository repository

    def "given nothing, when valida order delivery is created, then it should be saved in db"() {
        given:
        def command = OrderDeliveryTestProvider.getCreateOrderDeliveryCommand()

        when:
        def id = handler.create(command)

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.restaurantId == command.restaurantId
        savedOrder.deliveryDetails.address == command.deliveryDetails.address
    }
}
