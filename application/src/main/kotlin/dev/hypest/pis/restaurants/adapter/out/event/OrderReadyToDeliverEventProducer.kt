package dev.hypest.pis.restaurants.adapter.out.event

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.EventProducer
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient
interface OrderReadyToDeliverEventProducer : EventProducer {

    @Binding("order-ready")
    override fun produce(event: DomainEvent)

}
