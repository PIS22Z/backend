package dev.hypest.pis.restaurants.domain.products

import java.util.UUID


interface ProductRepository {

    fun add(product: Product)

    fun load(id: UUID): Product?

    fun save(product: Product)

    fun remove(id: UUID)
}
