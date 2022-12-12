package dev.hypest.pis.restaurants.infrastructure.db.products

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataProductRepository : CrudRepository<ProductEntity, UUID>
