@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.payments.PayOrderRequest
import dev.hypest.pis.payments.PaymentsApi
import dev.hypest.pis.payments.adapter.`in`.mapper.ActiveOrderMapper.mapToPayOrderCommand
import dev.hypest.pis.payments.domain.ordertopay.PayOrderHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/payments")
class PaymentsController(
    private val payOrderHandler: PayOrderHandler
) : PaymentsApi {

    @Post
    override fun payOrder(@Body request: PayOrderRequest): HttpResponse<UuidWrapper> {
        val command = mapToPayOrderCommand(request)
        val id = payOrderHandler.pay(command)
        return HttpResponse.created(UuidWrapper(id))
    }
}
