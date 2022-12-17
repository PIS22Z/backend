package dev.hypest.pis.restaurants.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.restaurants.ActiveOrderTestProvider
import dev.hypest.pis.restaurants.OrderReadyToDeliverEvent
import dev.hypest.pis.restaurants.domain.activeorder.ActiveOrderRepository
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverCommand
import dev.hypest.pis.restaurants.domain.activeorder.MarkOrderAsReadyToDeliverHandler
import jakarta.inject.Inject

class MarkOrderAsReadyToDeliverHandlerTest extends BaseTest {

    @Inject
    private MarkOrderAsReadyToDeliverHandler handler

    @Inject
    private ActiveOrderRepository repository

    @Inject
    DomainEventPublisher domainEventPublisher

    def "given existing order, when it is marked as ready to deliver, then it should be saved to db and event should be published"() {
        given:
        def existingOrder = ActiveOrderTestProvider.getAggregate()
        repository.add(existingOrder)

        when:
        def id = handler.markAsReady(new MarkOrderAsReadyToDeliverCommand(existingOrder.id))

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.readyToDeliver

        1 * domainEventPublisher.publish(_ as OrderReadyToDeliverEvent)
    }
}
