package dev.hypest.pis.orders.adapter.out.db

import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.logger
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.orders.adapter.out.mapper.DraftOrderMapper.mapToDraftOrder
import dev.hypest.pis.orders.adapter.out.mapper.DraftOrderMapper.mapToDraftOrderEntity
import dev.hypest.pis.orders.domain.draftorder.DraftOrder
import dev.hypest.pis.orders.domain.draftorder.DraftOrderRepository
import dev.hypest.pis.orders.infrastructure.db.draftorder.MicronautDataDraftOrderRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbDraftOrderRepository(
    private val repository: MicronautDataDraftOrderRepository,
    private val publisher: DomainEventPublisher
) : DraftOrderRepository {

    private val log by logger()

    override fun add(draftOrder: DraftOrder) {
        repository.save(mapToDraftOrderEntity(draftOrder))
        log.info("DraftOrder with ID=${draftOrder.id} added")
        publishAllDomainEvents(draftOrder)
    }

    override fun save(draftOrder: DraftOrder) {
        repository.update(mapToDraftOrderEntity(draftOrder))
        log.info("DraftOrder with ID=${draftOrder.id} saved")
        publishAllDomainEvents(draftOrder)
    }

    override fun load(id: UUID): DraftOrder? {
        return repository.findById(id).unwrap()?.let { mapToDraftOrder(it) }
    }

    private fun publishAllDomainEvents(draftOrder: DraftOrder) {
        draftOrder.getDomainEvents().forEach { publisher.publish(it) }
    }
}
