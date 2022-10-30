package dev.hypest.pis.orders.adapter.out.event

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.EventProducer
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient
interface OrderFinalizedEventProducer : EventProducer {

    @Binding("order-finalized")
    override fun produce(event: DomainEvent)

}
