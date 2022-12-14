@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.orders.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.orders.CreateOrderRequest
import dev.hypest.pis.orders.FinalizeOrderRequest
import dev.hypest.pis.orders.ModifyOrderItemRequest
import dev.hypest.pis.orders.OrderResponse
import dev.hypest.pis.orders.OrdersApi
import dev.hypest.pis.orders.adapter.`in`.mapper.DraftOrderMapper.mapToCreateOrderCommand
import dev.hypest.pis.orders.adapter.`in`.mapper.DraftOrderMapper.mapToFinalizeOrderCommand
import dev.hypest.pis.orders.adapter.`in`.mapper.DraftOrderMapper.mapToModifyOrderItemCommand
import dev.hypest.pis.orders.adapter.`in`.query.OrderQuery
import dev.hypest.pis.orders.domain.draftorder.AddItemToOrderHandler
import dev.hypest.pis.orders.domain.draftorder.CreateOrderHandler
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderHandler
import dev.hypest.pis.orders.domain.draftorder.RemoveItemFromOrderHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/orders")
class OrdersController(
    private val createOrderHandler: CreateOrderHandler,
    private val finalizeOrderHandler: FinalizeOrderHandler,
    private val addItemToOrderHandler: AddItemToOrderHandler,
    private val removeItemFromOrderHandler: RemoveItemFromOrderHandler,
    private val orderQuery: OrderQuery
) : OrdersApi {

    @Get("/{orderId}")
    override fun getOrder(@PathVariable orderId: UUID): HttpResponse<OrderResponse> {
        return orderQuery.getOrder(orderId).let { HttpResponse.ok(it) }
    }

    @Get
    override fun getOrders(): HttpResponse<List<OrderResponse>> {
        return orderQuery.getOrders().let { HttpResponse.ok(it) }
    }

    @Post
    override fun createOrder(@Body request: CreateOrderRequest): HttpResponse<UuidWrapper> {
        val command = mapToCreateOrderCommand(request)
        val id = createOrderHandler.create(command)
        return HttpResponse.created(UuidWrapper(id))
    }

    @Put("/{orderId}/add")
    override fun addProductToOrder(
        @PathVariable orderId: UUID,
        @Body request: ModifyOrderItemRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToModifyOrderItemCommand(orderId, request)
        val id = addItemToOrderHandler.addItem(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Put("/{orderId}/remove")
    override fun removeProductFromOrder(
        @PathVariable orderId: UUID,
        @Body request: ModifyOrderItemRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToModifyOrderItemCommand(orderId, request)
        val id = removeItemFromOrderHandler.removeItem(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Put("/{orderId}/finalize")
    override fun finalizeOrder(
        @PathVariable orderId: UUID,
        @Body request: FinalizeOrderRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToFinalizeOrderCommand(orderId, request)
        val id = finalizeOrderHandler.finalize(command)
        return HttpResponse.ok(UuidWrapper(id))
    }
}
