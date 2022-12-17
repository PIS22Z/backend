package dev.hypest.pis.payments.adapter.out.event

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.EventProducer
import dev.hypest.pis.configuration.RabbitmqConfig.Companion.ORDER_PAID
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient
interface OrderPaidEventProducer : EventProducer {

    @Binding(ORDER_PAID)
    override fun produce(event: DomainEvent)

}
