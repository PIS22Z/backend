package dev.hypest.pis.restaurants

import dev.hypest.pis.common.UuidWrapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/orders")
interface ActiveOrdersApi {

    @Put("/{orderId}/ready")
    fun markOrderAsReadyToDeliver(
        @PathVariable orderId: UUID
    ): HttpResponse<UuidWrapper>

    @Put("/{orderId}/reject")
    fun rejectActiveOrder(
        @PathVariable orderId: UUID
    ): HttpResponse<UuidWrapper>

    @Put("/{orderId}/accept")
    fun acceptActiveOrder(
        @PathVariable orderId: UUID
    ): HttpResponse<UuidWrapper>
}
