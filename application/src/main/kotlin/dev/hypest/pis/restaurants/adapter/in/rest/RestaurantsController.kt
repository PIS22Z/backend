@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.restaurants.adapter.`in`.rest

import dev.hypest.pis.common.UuidWrapper
import dev.hypest.pis.restaurants.adapter.`in`.mapper.ProductMapper.mapToCreateProductCommand
import dev.hypest.pis.restaurants.adapter.`in`.mapper.ProductMapper.mapToUpdateProductCommand
import dev.hypest.pis.restaurants.adapter.`in`.mapper.RestaurantMapper.mapToCreateRestaurantCommand
import dev.hypest.pis.restaurants.adapter.`in`.mapper.RestaurantMapper.mapToUpdateRestaurantCommand
import dev.hypest.pis.restaurants.adapter.`in`.query.ProductQuery
import dev.hypest.pis.restaurants.adapter.`in`.query.RestaurantQuery
import dev.hypest.pis.restaurants.domain.products.CreateProductHandler
import dev.hypest.pis.restaurants.domain.products.RemoveProductHandler
import dev.hypest.pis.restaurants.domain.products.UpdateProductHandler
import dev.hypest.pis.restaurants.domain.restaurants.CreateRestaurantHandler
import dev.hypest.pis.restaurants.domain.restaurants.RemoveRestaurantHandler
import dev.hypest.pis.restaurants.domain.restaurants.UpdateRestaurantHandler
import dev.hypest.pis.restaurants.product.CreateProductRequest
import dev.hypest.pis.restaurants.product.ProductResponse
import dev.hypest.pis.restaurants.product.UpdateProductRequest
import dev.hypest.pis.restaurants.restaurants.CreateRestaurantRequest
import dev.hypest.pis.restaurants.restaurants.RestaurantResponse
import dev.hypest.pis.restaurants.restaurants.RestaurantsApi
import dev.hypest.pis.restaurants.restaurants.UpdateRestaurantRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import java.util.UUID

@Suppress("LongParameterList")
@Controller("/restaurants")
class RestaurantsController(
    private val createRestaurantHandler: CreateRestaurantHandler,
    private val updateRestaurantHandler: UpdateRestaurantHandler,
    private val removeRestaurantHandler: RemoveRestaurantHandler,
    private val createProductHandler: CreateProductHandler,
    private val updateProductHandler: UpdateProductHandler,
    private val removeProductHandler: RemoveProductHandler,
    private val restaurantQuery: RestaurantQuery,
    private val productQuery: ProductQuery
) : RestaurantsApi {

    @Get
    override fun getAllRestaurants(): HttpResponse<List<RestaurantResponse>> =
        restaurantQuery.getRestaurants().let { HttpResponse.ok(it) }

    @Get("/{restaurantId}")
    override fun getRestaurantById(@PathVariable restaurantId: UUID): HttpResponse<RestaurantResponse> =
        restaurantQuery.getRestaurant(restaurantId).let { HttpResponse.ok(it) }

    @Post
    override fun createRestaurant(@Body restaurant: CreateRestaurantRequest): HttpResponse<UuidWrapper> {
        val command = mapToCreateRestaurantCommand(restaurant)
        val id = createRestaurantHandler.create(command)
        return HttpResponse.created(UuidWrapper(id))
    }

    @Delete("/{restaurantId}")
    override fun removeRestaurant(@PathVariable restaurantId: UUID): HttpResponse<Unit> {
        removeRestaurantHandler.remove(restaurantId)
        return HttpResponse.ok()
    }

    @Put("/{restaurantId}")
    override fun updateRestaurant(
        @PathVariable restaurantId: UUID,
        @Body request: UpdateRestaurantRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToUpdateRestaurantCommand(restaurantId, request)
        val id = updateRestaurantHandler.update(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Post("/{restaurantId}/products")
    override fun addProductToRestaurant(
        @PathVariable restaurantId: UUID,
        @Body request: CreateProductRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToCreateProductCommand(restaurantId, request)
        val id = createProductHandler.create(command)
        return HttpResponse.created(UuidWrapper(id))
    }

    @Get("/{restaurantId}/products")
    override fun getProductsFromRestaurant(@PathVariable restaurantId: UUID): HttpResponse<List<ProductResponse>> =
        productQuery.getProductsFromRestaurant(restaurantId).let { HttpResponse.ok(it) }

    @Get("/{restaurantId}/products/{productId}")
    override fun getProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID
    ): HttpResponse<ProductResponse> =
        productQuery.getProduct(restaurantId, productId).let { HttpResponse.ok(it) }

    @Put("/{restaurantId}/products/{productId}")
    override fun updateProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID,
        @Body request: UpdateProductRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToUpdateProductCommand(productId, request)
        val id = updateProductHandler.update(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    @Delete("/{restaurantId}/products/{productId}")
    override fun removeProductFromRestaurant(
        @PathVariable restaurantId: UUID,
        @PathVariable productId: UUID
    ): HttpResponse<Unit> {
        removeProductHandler.remove(productId)
        return HttpResponse.ok()
    }
}
