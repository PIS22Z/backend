package dev.hypest.pis.orders.domain

import dev.hypest.pis.BaseTest
import dev.hypest.pis.orders.DraftOrderTestProvider
import dev.hypest.pis.orders.domain.draftorder.DraftOrder
import dev.hypest.pis.orders.domain.draftorder.DraftOrderNotFoundException
import dev.hypest.pis.orders.domain.draftorder.DraftOrderRepository
import dev.hypest.pis.orders.domain.draftorder.OrderItem
import dev.hypest.pis.orders.domain.draftorder.RemoveItemFromOrderHandler
import jakarta.inject.Inject

class RemoveItemFromOrderHandlerTest extends BaseTest {

    @Inject
    private RemoveItemFromOrderHandler handler

    @Inject
    private DraftOrderRepository orderRepository

    def "given existing order, when part of OrderItem is removed, then quantity of order should be updated"() {
        given:
        def existingOrder = DraftOrder.new(UUID.randomUUID(), [new OrderItem(UUID.randomUUID(), 3)])
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getModifyOrderItemCommand(existingOrder.id, existingOrder.items.first().productId,  1)

        when:
        handler.removeItem(command)

        then:
        def savedOrder = orderRepository.load(command.orderId)
        savedOrder != null
        savedOrder.id == command.orderId
        savedOrder.items.size() == 1
        savedOrder.items[0].productId == existingOrder.items[0].productId
        savedOrder.items[0].quantity == existingOrder.items[0].quantity - command.product.quantity
    }

    def "given not existing order, when OrderItem is removed, then it should throw"() {
        given:
        def command = DraftOrderTestProvider.getModifyOrderItemCommand(UUID.randomUUID(), UUID.randomUUID(), 3)

        when:
        handler.removeItem(command)

        then:
        def e = thrown(DraftOrderNotFoundException)
        e.message == "Draft order with id ${command.orderId} not found"
    }

    def "given quantity bigger than existing, when OrderItem is removed, then it should remove the whole OrderItem"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getModifyOrderItemCommand(existingOrder.id, existingOrder.getItems().first().productId, 3)

        when:
        handler.removeItem(command)

        then:
        def savedOrder = orderRepository.load(command.orderId)
        savedOrder != null
        savedOrder.id == command.orderId
        savedOrder.items.size() == 0
    }

    def "given product id not existing, when OrderItem is removed, then nothing is changed"() {
        given:
        def existingOrder = DraftOrderTestProvider.getAggregate()
        orderRepository.add(existingOrder)

        def command = DraftOrderTestProvider.getModifyOrderItemCommand(existingOrder.id, UUID.randomUUID(), 1)

        when:
        handler.removeItem(command)

        then:
        def savedOrder = orderRepository.load(command.orderId)
        savedOrder != null
        savedOrder.id == command.orderId
        savedOrder.items.size() == 1
        savedOrder.items[0].productId == existingOrder.items[0].productId
        savedOrder.items[0].quantity == existingOrder.items[0].quantity
    }
}
