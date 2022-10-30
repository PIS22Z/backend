package dev.hypest.pis.common.eventaggregator

abstract class AggregateRoot : DomainEventAggregator {

    private val events: ArrayList<DomainEvent> = ArrayList()

    override fun clearDomainEvents() {
        events.clear()
    }

    override fun getDomainEvents(): ArrayList<DomainEvent> {
        return events
    }

    protected fun publishEvent(event: DomainEvent) {
        events.add(event)
    }

    protected fun publishEvents(events: Collection<DomainEvent>) {
        this.events.addAll(events)
    }
}
