package dev.hypest.pis.delivery.infrastructure.db.orderdelivery

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataOrderDeliveryRepository : CrudRepository<OrderDeliveryEntity, UUID>
