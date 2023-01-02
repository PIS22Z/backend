@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.delivery.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.delivery.AcceptOrderDeliveryRequest
import dev.hypest.pis.delivery.OrderDeliveriesApi
import dev.hypest.pis.delivery.OrderDeliveryOfferResponse
import dev.hypest.pis.delivery.adapter.`in`.mapper.OrderDeliveryMapper.mapToAcceptOrderDeliveryCommand
import dev.hypest.pis.delivery.adapter.`in`.query.OrderDeliveryOfferQuery
import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import java.util.UUID

@Controller("/deliveries")
class OrderDeliveriesController(
    private val orderDeliveryOfferQuery: OrderDeliveryOfferQuery,
    private val acceptOrderDeliveryHandler: AcceptOrderDeliveryHandler
) : OrderDeliveriesApi {

    @Get("/offer")
    override fun getOrderDeliveryOffer(@QueryValue courierAddress: String): HttpResponse<OrderDeliveryOfferResponse> {
        return orderDeliveryOfferQuery.getOrderDeliveryOffer(courierAddress)
            ?.let { HttpResponse.ok(it) } ?: HttpResponse.notFound()
    }

    @Put("/{orderDeliveryId}/accept")
    override fun acceptOrderDelivery(
        @PathVariable orderDeliveryId: UUID,
        @Body request: AcceptOrderDeliveryRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToAcceptOrderDeliveryCommand(orderDeliveryId, request)
        val id = acceptOrderDeliveryHandler.accept(command)
        return HttpResponse.ok(UuidWrapper(id))
    }
}
