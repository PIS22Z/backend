package dev.hypest.pis.orders.domain.draftorder

import java.util.UUID

class DraftOrderNotFoundException(orderId: UUID) :
    NoSuchElementException("Draft order with id $orderId not found")
