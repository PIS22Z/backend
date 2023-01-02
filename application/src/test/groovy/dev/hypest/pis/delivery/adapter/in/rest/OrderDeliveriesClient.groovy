package dev.hypest.pis.delivery.adapter.in.rest

import dev.hypest.pis.delivery.OrderDeliveriesApi
import io.micronaut.http.client.annotation.Client

@Client("/api/deliveries")
interface OrderDeliveriesClient extends OrderDeliveriesApi {

}
