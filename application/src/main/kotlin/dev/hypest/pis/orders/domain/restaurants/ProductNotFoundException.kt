package dev.hypest.pis.orders.domain.restaurants

import java.util.UUID

class ProductNotFoundException(id: UUID) : NoSuchElementException("Product with ID={$id} not found")
