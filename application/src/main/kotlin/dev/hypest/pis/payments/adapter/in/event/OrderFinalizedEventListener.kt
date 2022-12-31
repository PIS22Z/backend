@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.payments.adapter.`in`.event

import dev.hypest.pis.common.logger
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_FINALIZED
import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.payments.adapter.`in`.mapper.OrderFinalizedEventMapper.mapToCreateOrderToPayCommand
import dev.hypest.pis.payments.domain.ordertopay.CreateOrderToPayHandler
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class OrderFinalizedEventListener(
    private val createOrderToPayHandler: CreateOrderToPayHandler
) {

    private val log by logger()

    @Queue(ORDER_FINALIZED)
    fun receive(event: OrderFinalizedEvent) {
        log.info("Received event: $event")

        val command = mapToCreateOrderToPayCommand(event)
        createOrderToPayHandler.create(command)
    }
}
