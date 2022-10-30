package dev.hypest.pis.payments.adapter.out.db

import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.logger
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.payments.adapter.out.mapper.OrderToPayMapper.mapToOrderToPay
import dev.hypest.pis.payments.adapter.out.mapper.OrderToPayMapper.mapToOrderToPayEntity
import dev.hypest.pis.payments.domain.ordertopay.OrderToPay
import dev.hypest.pis.payments.domain.ordertopay.OrderToPayRepository
import dev.hypest.pis.payments.infrastructure.db.ordertopay.MicronautDataOrderToPayRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbOrderToPayToPayRepository(
    private val repository: MicronautDataOrderToPayRepository,
    private val publisher: DomainEventPublisher
) : OrderToPayRepository {

    private val log by logger()

    override fun add(orderToPay: OrderToPay) {
        repository.save(mapToOrderToPayEntity(orderToPay))
        log.info("OrderToPay with ID=${orderToPay.id} added")
        publishAllDomainEvents(orderToPay)
    }

    override fun save(orderToPay: OrderToPay) {
        repository.update(mapToOrderToPayEntity(orderToPay))
        log.info("OrderToPay with ID=${orderToPay.id} saved")
        publishAllDomainEvents(orderToPay)
    }

    override fun load(id: UUID): OrderToPay? {
        return repository.findById(id).unwrap()?.let { mapToOrderToPay(it) }
    }

    private fun publishAllDomainEvents(orderToPay: OrderToPay) {
        orderToPay.getDomainEvents().forEach { publisher.publish(it) }
    }
}
