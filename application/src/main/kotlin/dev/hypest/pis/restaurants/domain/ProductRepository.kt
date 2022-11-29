package dev.hypest.pis.restaurants.domain

import java.util.UUID


interface ProductRepository {

    fun add(product: Product)

    fun load(id: UUID): Product?
}
