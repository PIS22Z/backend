package dev.hypest.pis.payments

import dev.hypest.pis.common.UuidWrapper
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/payments")
interface PaymentsApi {

    @Post
    fun payOrder(@Body request: PayOrderRequest): HttpResponse<UuidWrapper>
}
