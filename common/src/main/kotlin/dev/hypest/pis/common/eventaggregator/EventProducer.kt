package dev.hypest.pis.common.eventaggregator

interface EventProducer {

    fun produce(event: DomainEvent)
}
