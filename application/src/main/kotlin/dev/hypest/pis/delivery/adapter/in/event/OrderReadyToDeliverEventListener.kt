@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.delivery.adapter.`in`.event

import dev.hypest.pis.common.logger
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_READY
import dev.hypest.pis.delivery.adapter.`in`.mapper.OrderReadyToDeliverEventMapper.mapToCreateOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.CreateOrderDeliveryHandler
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener


@RabbitListener
class OrderReadyToDeliverEventListener(
    private val createOrderDeliveryHandler: CreateOrderDeliveryHandler
) {


    private val log by logger()

    @Queue(ORDER_READY)
    fun receive(event: OrderReadyToDeliverEvent) {
        log.info("Received event: $event")

        val command = mapToCreateOrderDeliveryCommand(event)
        createOrderDeliveryHandler.create(command)
    }
}
