@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.event

import dev.hypest.pis.common.logger
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_REJECTED
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderCommand
import dev.hypest.pis.payments.domain.ordertopay.RefundOrderHandler
import dev.hypest.pis.restaurants.OrderRejectedEvent
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class OrderRejectedEventListener(
    private val refundOrderHandler: RefundOrderHandler
) {

    private val log by logger()

    @Queue(ORDER_REJECTED)
    fun receive(event: OrderRejectedEvent) {
        log.info("Received event: $event")

        val command = RefundOrderCommand(event.orderId)
        refundOrderHandler.refund(command)
    }
}
