package dev.hypest.pis.delivery

import dev.hypest.pis.common.UuidWrapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import java.util.UUID

@Controller("/deliveries")
interface OrderDeliveriesApi {

    // TODO dodać adres jako request param, żeby móc zwrócić najlepsze zamówienie
    @Get("/offer")
    fun getOrderDeliveryOffer(@QueryValue courierAddress: String): HttpResponse<OrderDeliveryOfferResponse>

    @Put("/{orderDeliveryId}/accept")
    fun acceptOrderDelivery(
        @PathVariable orderDeliveryId: UUID,
        @Body request: AcceptOrderDeliveryRequest
    ): HttpResponse<UuidWrapper>

    @Put("/{orderDeliveryId}/start")
    fun startOrderDelivery(
        @PathVariable orderDeliveryId: UUID
    ): HttpResponse<UuidWrapper>
}
