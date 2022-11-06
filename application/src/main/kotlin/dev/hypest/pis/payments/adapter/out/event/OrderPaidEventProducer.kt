package dev.hypest.pis.payments.adapter.out.event

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.EventProducer
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient
interface OrderPaidEventProducer : EventProducer {

    @Binding("order-paid")
    override fun produce(event: DomainEvent)

}
