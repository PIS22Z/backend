package dev.hypest.pis.common.eventaggregator

interface DomainEventAggregator {

    fun clearDomainEvents()

    fun getDomainEvents(): List<DomainEvent>

}
