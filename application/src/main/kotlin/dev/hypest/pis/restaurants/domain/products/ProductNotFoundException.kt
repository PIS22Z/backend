package dev.hypest.pis.restaurants.domain.products

import java.util.UUID

class ProductNotFoundException(id: UUID) : NoSuchElementException("Product with ID={$id} not found")
