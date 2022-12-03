package dev.hypest.pis.restaurants.restaurants

data class UpdateRestaurantRequest(
    val name: String,
    val description: String,
    val logoUrl: String,
    val city: String,
    val street: String,
    val number: String,
)
