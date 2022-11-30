package dev.hypest.pis.restaurants.infrastructure.db.activeorder

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataActiveOrderRepository : CrudRepository<ActiveOrderEntity, UUID>
