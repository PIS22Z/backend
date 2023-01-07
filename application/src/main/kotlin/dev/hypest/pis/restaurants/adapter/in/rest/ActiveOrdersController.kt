@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.restaurants.ActiveOrdersApi
import dev.hypest.pis.restaurants.domain.activeorder.AcceptActiveOrderCommand
import dev.hypest.pis.restaurants.domain.activeorder.AcceptActiveOrderHandler
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverCommand
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverHandler
import dev.hypest.pis.restaurants.domain.activeorder.RejectActiveOrderCommand
import dev.hypest.pis.restaurants.domain.activeorder.RejectActiveOrderHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/orders")
class ActiveOrdersController(
    private val markOrderAsReadyToDeliverHandler: MarkOrderAsReadyToDeliverHandler,
    private val rejectActiveOrderHandler: RejectActiveOrderHandler,
    private val acceptActiveOrderHandler: AcceptActiveOrderHandler
) : ActiveOrdersApi {

    @Put("/{orderId}/ready")
    override fun markOrderAsReadyToDeliver(@PathVariable orderId: UUID): HttpResponse<UuidWrapper> {
        val command = MarkOrderAsReadyToDeliverCommand(orderId)
        val id = markOrderAsReadyToDeliverHandler.markAsReady(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Put("/{orderId}/reject")
    override fun rejectActiveOrder(orderId: UUID): HttpResponse<UuidWrapper> {
        val command = RejectActiveOrderCommand(orderId)
        val id = rejectActiveOrderHandler.reject(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Put("/{orderId}/accept")
    override fun acceptActiveOrder(orderId: UUID): HttpResponse<UuidWrapper> {
        val command = AcceptActiveOrderCommand(orderId)
        val id = acceptActiveOrderHandler.accept(command)
        return HttpResponse.ok(UuidWrapper(id))
    }
}
