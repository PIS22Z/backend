package dev.hypest.pis.restaurants.adapter.out.event

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.EventProducer
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_REJECTED
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient
interface OrderRejectedEventProducer : EventProducer {

    @Binding(ORDER_REJECTED)
    override fun produce(event: DomainEvent)

}
