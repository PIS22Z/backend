package dev.hypest.pis.orders.adapter.in.rest

import dev.hypest.pis.orders.OrdersApi
import io.micronaut.http.client.annotation.Client

@Client("/api/orders")
interface OrdersClient extends OrdersApi {

}
