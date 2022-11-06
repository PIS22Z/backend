package dev.hypest.pis.common.eventaggregator

interface DomainEventPublisher {

    fun publish(event: DomainEvent)
}
