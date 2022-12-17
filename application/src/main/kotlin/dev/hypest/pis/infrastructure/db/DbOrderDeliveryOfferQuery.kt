package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.delivery.OrderDeliveryOfferResponse
import dev.hypest.pis.delivery.adapter.`in`.query.OrderDeliveryOfferQuery
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.MicronautDataOrderDeliveryRepository
import jakarta.inject.Singleton

@Singleton
class DbOrderDeliveryOfferQuery(
    private val orderDeliveryRepository: MicronautDataOrderDeliveryRepository
) : OrderDeliveryOfferQuery {

    override fun getOrderDeliveryOffer(courierAddress: String): OrderDeliveryOfferResponse {
        val deliveries = orderDeliveryRepository.findAll().filter { it.assignedCourierId == null }
        val selectedDelivery = deliveries.first()
        // TODO tutaj kiedyś będzie bardziej zaawansowany algorytm wyboru najlepszego zamówienia
        // trzeba będzie też nakładać locka na zamówienie, żeby nie było sytuacji, że dwaj kurierzy
        // wybiorą to samo zamówienie

        return OrderDeliveryOfferResponse(
            id = selectedDelivery.id,
            restaurantId = selectedDelivery.restaurantId,
            deliveryDetails = OrderDeliveryOfferResponse.DeliveryDetails(
                address = selectedDelivery.deliveryDetails.address
            )
        )
    }
}
