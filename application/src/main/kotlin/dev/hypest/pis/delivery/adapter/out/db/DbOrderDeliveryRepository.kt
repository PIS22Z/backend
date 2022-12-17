package dev.hypest.pis.delivery.adapter.out.db

import dev.hypest.pis.common.logger
import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.delivery.adapter.out.mapper.OrderDeliveryMapper.mapToOrderDeliveryEntity
import dev.hypest.pis.delivery.adapter.out.mapper.OrderDeliveryMapper.mapToOrderDelivery
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDelivery
import dev.hypest.pis.delivery.domain.orderdelivery.OrderDeliveryRepository
import dev.hypest.pis.delivery.infrastructure.db.orderdelivery.MicronautDataOrderDeliveryRepository
import jakarta.inject.Singleton
import java.util.UUID


@Singleton
class DbOrderDeliveryRepository(
    private val repository: MicronautDataOrderDeliveryRepository,
    private val publisher: DomainEventPublisher
) : OrderDeliveryRepository {

    private val log by logger()

    override fun add(orderDelivery: OrderDelivery) {
        repository.save(mapToOrderDeliveryEntity(orderDelivery))
        log.info("OrderDelivery with ID=${orderDelivery.id} added")
        publishAllDomainEvents(orderDelivery)
    }

    override fun save(orderDelivery: OrderDelivery) {
        repository.update(mapToOrderDeliveryEntity(orderDelivery))
        log.info("OrderDelivery with ID=${orderDelivery.id} saved")
        publishAllDomainEvents(orderDelivery)
    }

    override fun load(id: UUID): OrderDelivery? {
        return repository.findById(id).unwrap()?.let { mapToOrderDelivery(it) }
    }

    private fun publishAllDomainEvents(orderDelivery: OrderDelivery) {
        orderDelivery.getDomainEvents().forEach { publisher.publish(it) }
    }
}
