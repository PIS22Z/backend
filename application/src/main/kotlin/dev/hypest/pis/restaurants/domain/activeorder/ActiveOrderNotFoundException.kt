package dev.hypest.pis.restaurants.domain.activeorder

import java.util.UUID

class ActiveOrderNotFoundException(orderId: UUID) :
    NoSuchElementException("Active order with id $orderId not found")
