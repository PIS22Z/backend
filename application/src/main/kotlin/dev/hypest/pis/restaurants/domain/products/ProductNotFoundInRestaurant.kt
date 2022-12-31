package dev.hypest.pis.restaurants.domain.products

import java.util.UUID

class ProductNotFoundInRestaurant(restaurantId: UUID, productId: UUID)
    : NoSuchElementException("Product with ID={$productId} not found in restaurant with ID={$restaurantId}")
