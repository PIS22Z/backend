package dev.hypest.pis.restaurants.adapter.out.mapper

import dev.hypest.pis.restaurants.domain.restaurants.Restaurant
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.RestaurantEntity

object RestaurantMapper {

    fun mapToRestaurantEntity(restaurant: Restaurant) = RestaurantEntity(
        id = restaurant.id,
        name = restaurant.name,
        city = restaurant.city,
        street = restaurant.street,
        number = restaurant.number,
        products = restaurant.products.map(ProductMapper::mapToProductEntity),
        logoUrl = restaurant.logoUrl,
        description = restaurant.description
    )

    fun mapToRestaurant(restaurantEntity: RestaurantEntity) = Restaurant(
        id = restaurantEntity.id,
        name = restaurantEntity.name,
        city = restaurantEntity.city,
        street = restaurantEntity.street,
        number = restaurantEntity.number,
        products = restaurantEntity.products.map(ProductMapper::mapToProduct).toMutableList(),
        logoUrl = restaurantEntity.logoUrl,
        description = restaurantEntity.description
    )
}
