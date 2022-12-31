package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.common.unwrap
import dev.hypest.pis.restaurants.adapter.`in`.query.RestaurantQuery
import dev.hypest.pis.restaurants.domain.restaurants.RestaurantNotFoundException
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.MicronautDataRestaurantRepository
import dev.hypest.pis.restaurants.product.ProductResponse
import dev.hypest.pis.restaurants.restaurants.RestaurantResponse
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbRestaurantQuery(
    private val restaurantRepository: MicronautDataRestaurantRepository,
) : RestaurantQuery {
    override fun getRestaurants(): List<RestaurantResponse> =
        restaurantRepository.findAll().map { restaurant ->
            RestaurantResponse(
                id = restaurant.id,
                name = restaurant.name,
                description = restaurant.description,
                logoUrl = restaurant.logoUrl,
                products = restaurant.products.map { product ->
                    ProductResponse(
                        id = product.id,
                        name = product.name,
                        photoUrl = product.photoUrl,
                        price = product.price
                    )
                },
                city = restaurant.city,
                street = restaurant.street,
                number = restaurant.number,
            )
        }

    @Throws(RestaurantNotFoundException::class)
    override fun getRestaurant(restaurantId: UUID): RestaurantResponse {
        val restaurant =
            restaurantRepository.findById(restaurantId).unwrap()
                ?: throw RestaurantNotFoundException(restaurantId)
        return RestaurantResponse(
            id = restaurant.id,
            name = restaurant.name,
            description = restaurant.description,
            logoUrl = restaurant.logoUrl,
            products = restaurant.products.map { ProductResponse(it.id, it.name, it.photoUrl, it.price) },
            city = restaurant.city,
            street = restaurant.street,
            number = restaurant.number,
        )
    }
}
