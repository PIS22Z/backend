package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.delivery.OrderDeliveryOfferResponse
import dev.hypest.pis.delivery.adapter.`in`.query.OrderDeliveryOfferQuery
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.MicronautDataOrderDeliveryRepository
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.MicronautDataRestaurantRepository
import jakarta.inject.Singleton

@Singleton
class DbOrderDeliveryOfferQuery(
    private val orderDeliveryRepository: MicronautDataOrderDeliveryRepository,
    private val restaurantRepository: MicronautDataRestaurantRepository
) : OrderDeliveryOfferQuery {

    override fun getOrderDeliveryOffer(courierAddress: String): OrderDeliveryOfferResponse? {
        val deliveries = orderDeliveryRepository.findAll().filter { it.assignedCourierId == null }
        val selectedDelivery = deliveries.firstOrNull() ?: return null
        val restaurant = restaurantRepository.findById(selectedDelivery.restaurantId).unwrap()
        val restaurantAddress =
            if (restaurant != null) "${restaurant.city}, ${restaurant.street} ${restaurant.number}" else null

        return OrderDeliveryOfferResponse(
            id = selectedDelivery.id,
            restaurantId = selectedDelivery.restaurantId,
            restaurantName = restaurant?.name,
            restaurantAddress = restaurantAddress,
            deliveryDetails = OrderDeliveryOfferResponse.DeliveryDetails(
                address = selectedDelivery.deliveryDetails.address
            )
        )
    }
}
