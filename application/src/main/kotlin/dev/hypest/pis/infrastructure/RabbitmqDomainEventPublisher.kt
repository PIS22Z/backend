package dev.hypest.pis.infrastructure

import dev.hypest.pis.common.eventaggregator.DomainEvent
import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.eventaggregator.EventProducer
import dev.hypest.pis.common.logger
import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.orders.adapter.out.event.OrderFinalizedEventProducer
import dev.hypest.pis.payments.OrderPaidEvent
import dev.hypest.pis.payments.adapter.out.event.OrderPaidEventProducer
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent
import dev.hypest.pis.restaurants.OrderRejectedEvent
import dev.hypest.pis.restaurants.adapter.out.event.OrderReadyToDeliverEventProducer
import dev.hypest.pis.restaurants.adapter.out.event.OrderRejectedEventProducer
import io.micronaut.context.annotation.Bean

@Bean
class RabbitmqDomainEventPublisher(
    private val orderFinalizedEventProducer: OrderFinalizedEventProducer,
    private val orderPaidEventProducer: OrderPaidEventProducer,
    private val orderReadyToDeliverEventProducer: OrderReadyToDeliverEventProducer,
    private val orderRejectedEventProducer: OrderRejectedEventProducer,
) : DomainEventPublisher {

    private val log by logger()

    override fun publish(event: DomainEvent) {
        val producer = getProducer(event)
        producer?.let {
            log.info(
                "Domain event ${event.javaClass.simpleName} with ID=${event.id} " +
                        "for aggregate ${event.aggregateId} published at ${event.createdAt}"
            )
            it.produce(event)
        } ?: log.warning("No producer found for event $event")
    }

    private fun getProducer(event: DomainEvent): EventProducer? {
        return when (event) {
            is OrderFinalizedEvent -> orderFinalizedEventProducer
            is OrderPaidEvent -> orderPaidEventProducer
            is OrderReadyToDeliverEvent -> orderReadyToDeliverEventProducer
            is OrderRejectedEvent -> orderRejectedEventProducer
            else -> null
        }
    }
}
