package dev.hypest.pis.restaurants.adapter.in.rest

import dev.hypest.pis.restaurants.restaurants.RestaurantsApi
import io.micronaut.http.client.annotation.Client

@Client("/api/restaurants")
interface RestaurantClient extends RestaurantsApi {

}
