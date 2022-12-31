package dev.hypest.pis.restaurants.adapter.out.db

import dev.hypest.pis.common.eventaggregator.DomainEventPublisher
import dev.hypest.pis.common.logger
import dev.hypest.pis.common.unwrap
import dev.hypest.pis.restaurants.adapter.out.mapper.RestaurantMapper
import dev.hypest.pis.restaurants.domain.restaurants.Restaurant
import dev.hypest.pis.restaurants.domain.restaurants.RestaurantRepository
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.MicronautDataRestaurantRepository
import jakarta.inject.Singleton
import java.util.UUID

@Singleton
class DbRestaurantRepository(
    private val repository: MicronautDataRestaurantRepository,
    private val publisher: DomainEventPublisher
) : RestaurantRepository {
    private val log by logger()

    override fun add(restaurant: Restaurant) {
        repository.save(RestaurantMapper.mapToRestaurantEntity(restaurant))
        log.info("Restaurant with ID=${restaurant.id} added")
        publishAllDomainEvents(restaurant)
    }

    override fun load(id: UUID): Restaurant? =
        repository.findById(id).unwrap()?.let { RestaurantMapper.mapToRestaurant(it) }

    override fun save(restaurant: Restaurant) {
        repository.update(RestaurantMapper.mapToRestaurantEntity(restaurant))
        log.info("Restaurant with ID=${restaurant.id} saved")
        publishAllDomainEvents(restaurant)
    }

    override fun remove(id: UUID) {
        repository.deleteById(id)
        log.info("Restaurant with ID=$id removed")
    }

    private fun publishAllDomainEvents(restaurant: Restaurant) =
        restaurant.getDomainEvents().forEach { publisher.publish(it) }
}
