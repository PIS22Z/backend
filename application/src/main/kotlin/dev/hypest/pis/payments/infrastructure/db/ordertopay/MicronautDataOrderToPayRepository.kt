package dev.hypest.pis.payments.infrastructure.db.ordertopay

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataOrderToPayRepository : CrudRepository<OrderToPayEntity, UUID>
