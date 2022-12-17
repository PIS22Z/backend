@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.restaurants.ActiveOrdersApi
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverCommand
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/orders")
class ActiveOrdersController(
    private val markOrderAsReadyToDeliverHandler: MarkOrderAsReadyToDeliverHandler
) : ActiveOrdersApi {

    @Put("/{orderId}/ready")
    override fun markOrderAsReadyToDeliver(@PathVariable orderId: UUID): HttpResponse<UuidWrapper> {
        val command = MarkOrderAsReadyToDeliverCommand(orderId)
        val id = markOrderAsReadyToDeliverHandler.markAsReady(command)
        return HttpResponse.ok(UuidWrapper(id))
    }
}
