package dev.hypest.pis.orders.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.orders.DraftOrderTestProvider
import dev.hypest.pis.orders.domain.draftorder.AddItemToOrderHandler
import dev.hypest.pis.orders.domain.draftorder.DraftOrderNotFoundException
import dev.hypest.pis.orders.domain.draftorder.DraftOrderRepository
import jakarta.inject.Inject


class AddItemToOrderHandlerTest extends BaseTest {

    @Inject
    private AddItemToOrderHandler handler

    @Inject
    private DraftOrderRepository orderRepository

    def "given existing order, when OrderItem is added, then items of order should be updated"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getModifyOrderItemCommand(existingOrder.id,  UUID.randomUUID(), 1)

        when:
        handler.addItem(command)

        then:
        def savedOrder = orderRepository.load(command.orderId)
        savedOrder != null
        savedOrder.id == command.orderId
        savedOrder.items.size() == 2
        savedOrder.items[1].productId == command.product.productId
        savedOrder.items[1].quantity == command.product.quantity
    }

    def "given not existing order, when OrderItem is added, then it should throw"() {
        given:
        def command = DraftOrderTestProvider.getModifyOrderItemCommand(UUID.randomUUID(), UUID.randomUUID(), 1)

        when:
        handler.addItem(command)

        then:
        def e = thrown(DraftOrderNotFoundException)
        e.message == "Draft order with id ${command.orderId} not found"
    }

    def "given existing order, when an OrderItem with existing productId is added, then it should increase quantity"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getModifyOrderItemCommand(existingOrder.id, existingOrder.items[0].productId, 1)

        when:
        handler.addItem(command)

        then:
        def savedOrder = orderRepository.load(command.orderId)
        savedOrder != null
        savedOrder.id == command.orderId
        savedOrder.items.size() == 1
        savedOrder.items[0].productId == command.product.productId
        savedOrder.items[0].quantity == existingOrder.items[0].quantity + command.product.quantity
    }
}

