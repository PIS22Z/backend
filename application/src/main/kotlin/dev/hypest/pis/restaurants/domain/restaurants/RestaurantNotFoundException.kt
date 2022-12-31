package dev.hypest.pis.restaurants.domain.restaurants

import java.util.UUID

class RestaurantNotFoundException(id: UUID) : NoSuchElementException("Draft restaurant with id $id not found")
