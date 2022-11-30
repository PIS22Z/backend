@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.mapper

import dev.hypest.pis.payments.PayOrderRequest
import dev.hypest.pis.payments.domain.ordertopay.DeliveryDetails
import dev.hypest.pis.payments.domain.ordertopay.PayOrderCommand

object ActiveOrderMapper {

    fun mapToPayOrderCommand(
        request: PayOrderRequest
    ): PayOrderCommand = PayOrderCommand(
        orderId = request.orderId
    )
}
