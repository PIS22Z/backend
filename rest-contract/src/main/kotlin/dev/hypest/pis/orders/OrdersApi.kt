package dev.hypest.pis.orders

import dev.hypest.pis.common.UuidWrapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/api/orders")
interface OrdersApi {

    @Get
    fun helloWorld(): HttpResponse<String>

    @Post
    fun createOrder(@Body request: CreateOrderRequest): HttpResponse<UuidWrapper>

    @Put("/{orderId}/finalize")
    fun finalizeOrder(
        @PathVariable orderId: UUID,
        @Body request: FinalizeOrderRequest
    ): HttpResponse<UuidWrapper>

    // TODO PUT: dodaj produkt do koszyka

    // TODO PUT: update delivery details

    // TODO GET: active order (only one at a time0
}
