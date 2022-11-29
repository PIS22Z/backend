package dev.hypest.pis.orders.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.infrastructure.RabbitmqDomainEventPublisher
import dev.hypest.pis.orders.DraftOrderTestProvider
import dev.hypest.pis.orders.OrderFinalizedEvent
import dev.hypest.pis.orders.domain.draftorder.DraftOrderNotFoundException
import dev.hypest.pis.orders.domain.draftorder.DraftOrderRepository
import dev.hypest.pis.orders.domain.draftorder.FinalizeOrderHandler
import dev.hypest.pis.orders.domain.restaurants.OrdersToRestaurantsPort
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class FinalizeOrderHandlerTest extends BaseTest {

    @Inject
    private FinalizeOrderHandler finalizeOrderHandler

    @Inject
    private DraftOrderRepository orderRepository

    @Inject
    DomainEventPublisher domainEventPublisher

    @Inject
    OrdersToRestaurantsPort ordersToRestaurantsPort

    def "given existing order and existing products, when order is finalized, then it should be saved in db and event should be published"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getFinalizeOrderCommand(existingOrder.id)

        when:
        def id = finalizeOrderHandler.finalize(command)

        then:
        def savedOrder = orderRepository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.isFinalized()

        1 * ordersToRestaurantsPort.getProduct(_ as UUID) >> DraftOrderTestProvider.getProduct()
        1 * domainEventPublisher.publish(_ as OrderFinalizedEvent)
    }

    def "given not existing order, when order is finalized, then it should throw"() {
        given:
        def command = DraftOrderTestProvider.getFinalizeOrderCommand(UUID.randomUUID())

        when:
        finalizeOrderHandler.finalize(command)

        then:
        def e = thrown(DraftOrderNotFoundException)
        e.message == "Draft order with id ${command.orderId} not found"

        0 * domainEventPublisher.publish(_ as OrderFinalizedEvent)
    }

    def "given not existing products, when order is finalized, then it should be saved in db and event should be published"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getFinalizeOrderCommand(existingOrder.id)

        when:
        finalizeOrderHandler.finalize(command)

        then:
        def e = thrown(IllegalStateException)
        e.message == "Product with ID=${existingOrder.items[0].productId} not found"

        1 * ordersToRestaurantsPort.getProduct(_ as UUID) >> null
        0 * domainEventPublisher.publish(_ as OrderFinalizedEvent)
    }

    @MockBean(RabbitmqDomainEventPublisher)
    DomainEventPublisher domainEventPublisher() {
        Mock(DomainEventPublisher)
    }

    @MockBean(OrdersToRestaurantsPort)
    OrdersToRestaurantsPort ordersToRestaurantsPort() {
        Mock(OrdersToRestaurantsPort)
    }
}
