package dev.hypest.pis.restaurants.infrastructure.db.restaurant

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataRestaurantRepository : CrudRepository<RestaurantEntity, UUID>
