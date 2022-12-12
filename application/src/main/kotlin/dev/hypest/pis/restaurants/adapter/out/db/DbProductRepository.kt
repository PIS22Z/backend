package dev.hypest.pis.restaurants.adapter.out.db

import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.logger
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.restaurants.adapter.out.mapper.ProductMapper.mapToProduct
import dev.hypest.pis.restaurants.adapter.out.mapper.ProductMapper.mapToProductEntity
import dev.hypest.pis.restaurants.domain.products.Product
import dev.hypest.pis.restaurants.domain.products.ProductRepository
import dev.hypest.pis.restaurants.infrastructure.db.products.MicronautDataProductRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbProductRepository(
    private val repository: MicronautDataProductRepository,
    private val publisher: DomainEventPublisher
) : ProductRepository {

    private val log by logger()

    override fun add(product: Product) {
        repository.save(mapToProductEntity(product))
        log.info("Product with ID=${product.id} added")
        publishAllDomainEvents(product)
    }

    override fun load(id: UUID): Product? {
        return repository.findById(id).unwrap()?.let { mapToProduct(it) }
    }

    private fun publishAllDomainEvents(product: Product) {
        product.getDomainEvents().forEach { publisher.publish(it) }
    }
}
