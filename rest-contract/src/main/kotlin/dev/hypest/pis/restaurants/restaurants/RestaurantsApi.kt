package dev.hypest.pis.restaurants.restaurants

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.restaurants.product.CreateProductRequest
import dev.hypest.pis.restaurants.product.ProductResponse
import dev.hypest.pis.restaurants.product.UpdateProductRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Controller("/restaurants")
interface RestaurantsApi {

    @Get
    fun getAllRestaurants(): HttpResponse<List<RestaurantResponse>>

    @Get("/{restaurantId}")
    fun getRestaurantById(@PathVariable restaurantId: UUID): HttpResponse<RestaurantResponse>

    @Post
    fun createRestaurant(@Body restaurant: CreateRestaurantRequest): HttpResponse<UuidWrapper>

    @Delete("/{restaurantId}")
    fun deleteRestaurant(@PathVariable restaurantId: UUID): HttpResponse<Unit>

    @Put("/{restaurantId}")
    fun updateRestaurant(
        @PathVariable restaurantId: UUID,
        @Body request: UpdateRestaurantRequest
    ): HttpResponse<UuidWrapper>

    @Post("/{restaurantId}/products")
    fun addProductToRestaurant(
        @PathVariable restaurantId: UUID,
        @Body request: CreateProductRequest
    ): HttpResponse<UuidWrapper>

    @Get("/{restaurantId}/products")
    fun getProductsFromRestaurant(@PathVariable restaurantId: UUID): HttpResponse<List<ProductResponse>>

    @Get("/{restaurantId}/products/{productId}")
    fun getProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID
    ): HttpResponse<ProductResponse>

    @Put("/{restaurantId}/products/{productId}")
    fun updateProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID,
        @Body request: UpdateProductRequest
    ): HttpResponse<UuidWrapper>

    @Delete("/{restaurantId}/products/{productId}")
    fun deleteProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID
    ): HttpResponse<Unit>
}
