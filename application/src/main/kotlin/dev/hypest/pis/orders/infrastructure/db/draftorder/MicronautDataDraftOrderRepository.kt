package dev.hypest.pis.orders.infrastructure.db.draftorder

import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import java.util.UUID

@MongoRepository
interface MicronautDataDraftOrderRepository : CrudRepository<DraftOrderEntity, UUID>
