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

@Controller("/orders")
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

    @Put("/{orderId}/add")
    fun addProductToOrder(
        @PathVariable orderId: UUID,
        @Body request: ModifyOrderItemRequest
    ): HttpResponse<UuidWrapper>

    @Put("/{orderId}/remove")
    fun removeProductFromOrder(
        @PathVariable orderId: UUID,
        @Body request: ModifyOrderItemRequest
    ): HttpResponse<UuidWrapper>
    // TODO PUT: update delivery details

    // TODO GET: active order (only one at a time0
}
