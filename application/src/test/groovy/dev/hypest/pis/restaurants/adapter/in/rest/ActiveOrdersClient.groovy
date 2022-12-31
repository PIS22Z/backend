package dev.hypest.pis.restaurants.adapter.in.rest

import dev.hypest.pis.restaurants.ActiveOrdersApi
import io.micronaut.http.client.annotation.Client

@Client("/api/orders")
interface ActiveOrdersClient extends ActiveOrdersApi {
}
