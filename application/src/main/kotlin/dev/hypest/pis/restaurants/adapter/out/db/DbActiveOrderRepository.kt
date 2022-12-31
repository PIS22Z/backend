package dev.hypest.pis.restaurants.adapter.out.db

import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.logger
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.restaurants.domain.activeorder.ActiveOrder
import dev.hypest.pis.restaurants.domain.activeorder.ActiveOrderRepository
import dev.hypest.pis.restaurants.infrastructure.db.activeorder.MicronautDataActiveOrderRepository
import dev.hypest.pis.restaurants.adapter.out.mapper.ActiveOrderMapper.mapToActiveOrder
import dev.hypest.pis.restaurants.adapter.out.mapper.ActiveOrderMapper.mapToActiveOrderEntity
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbActiveOrderRepository(
    private val repository: MicronautDataActiveOrderRepository,
    private val publisher: DomainEventPublisher
) : ActiveOrderRepository {

    private val log by logger()

    override fun add(activeOrder: ActiveOrder) {
        repository.save(mapToActiveOrderEntity(activeOrder))
        log.info("ActiveOrder with ID=${activeOrder.id} added")
        publishAllDomainEvents(activeOrder)
    }

    override fun save(activeOrder: ActiveOrder) {
        repository.update(mapToActiveOrderEntity(activeOrder))
        log.info("ActiveOrder with ID=${activeOrder.id} added")
        publishAllDomainEvents(activeOrder)
    }

    override fun load(id: UUID): ActiveOrder? {
        return repository.findById(id).unwrap()?.let { mapToActiveOrder(it) }
    }

    private fun publishAllDomainEvents(activeOrder: ActiveOrder) {
        activeOrder.getDomainEvents().forEach { publisher.publish(it) }
    }
}
