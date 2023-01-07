package dev.hypest.pis.restaurants.domain.activeorder

import dev.hypest.pis.BaseTest
import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.restaurants.ActiveOrderTestProvider
import dev.hypest.pis.restaurants.OrderRejectedEvent
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class RejectActiveOrderHandlerTest extends BaseTest {

    @Inject
    private RejectActiveOrderHandler handler

    @Inject
    private ActiveOrderRepository repository

    @Inject
    DomainEventPublisher domainEventPublisher

    def "given paid order, when is it rejected by restaurants, then is should be saved to db"() {
        given:
        def existingOrder = ActiveOrderTestProvider.getAggregate(isPaid: true)
        repository.add(existingOrder)

        when:
        def id = handler.reject(new RejectActiveOrderCommand(existingOrder.id))

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.isAccepted() == false

        1 * domainEventPublisher.publish(_ as OrderRejectedEvent)
    }

    @MockBean(DomainEventPublisher)
    DomainEventPublisher domainEventPublisher() {
        Mock(DomainEventPublisher)
    }
}
