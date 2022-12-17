@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.event

import dev.hypest.pis.common.logger
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_PAID
import dev.hypest.pis.payments.OrderPaidEvent
import dev.hypest.pis.restaurants.adapter.`in`.mapper.OrderPaidEventMapper.mapToCreateActiveOrderCommand
import dev.hypest.pis.restaurants.domain.activeorder.CreateActiveOrderHandler
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class OrderPaidEventListener(
    private val createActiveOrderHandler: CreateActiveOrderHandler
) {

    private val log by logger()

    @Queue(ORDER_PAID)
    fun receive(event: OrderPaidEvent) {
        log.info("Received event: $event")

        val command = mapToCreateActiveOrderCommand(event)
        createActiveOrderHandler.create(command)
    }
}
