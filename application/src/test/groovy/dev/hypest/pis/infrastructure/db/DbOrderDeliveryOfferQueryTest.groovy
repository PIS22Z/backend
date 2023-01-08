package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.BaseTest
import dev.hypest.pis.delivery.adapter.in.query.OrderDeliveryOfferQuery
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.DeliveryDetails
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.MicronautDataOrderDeliveryRepository
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.OrderDeliveryEntity
import jakarta.inject.Inject

class DbOrderDeliveryOfferQueryTest extends BaseTest {

    @Inject
    private OrderDeliveryOfferQuery orderDeliveryOfferQuery

    @Inject
    private MicronautDataOrderDeliveryRepository orderDeliveryRepository

    def "given active order delivery without assigned courier, when offer is retrieved, then it should return delivery"() {
        given:
        def delivery = new OrderDeliveryEntity(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("deliveryAddress"), null, false, false)
        orderDeliveryRepository.save(delivery)

        when:
        def offer = orderDeliveryOfferQuery.getOrderDeliveryOffer("courierAddress")

        then:
        offer != null
        offer.id == delivery.id
    }

    def "given active order delivery with assigned courier, when offer is retrieved, then should return null"() {
        given:
        def delivery = new OrderDeliveryEntity(UUID.randomUUID(), UUID.randomUUID(), new DeliveryDetails("deliveryAddress"), UUID.randomUUID(), false, false)
        orderDeliveryRepository.save(delivery)

        when:
        def offer = orderDeliveryOfferQuery.getOrderDeliveryOffer("courierAddress")

        then:
        offer == null
    }

}
