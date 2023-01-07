package dev.hypest.pis.restaurants.domain.activeorder
import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.ActiveOrderTestProvider
import jakarta.inject.Inject

class AcceptActiveOrderHandlerTest extends BaseTest {

    @Inject
    private AcceptActiveOrderHandler handler

    @Inject
    private ActiveOrderRepository repository

    def "given paid order, when is it accepted by restaurants, then is should be saved to db"() {
        given:
        def existingOrder = ActiveOrderTestProvider.getAggregate(isPaid: true)
        repository.add(existingOrder)

        when:
        def id = handler.accept(new AcceptActiveOrderCommand(existingOrder.id))

        then:
        def savedOrder = repository.load(id)
        savedOrder != null
        savedOrder.id == id
        savedOrder.accepted
    }
}
