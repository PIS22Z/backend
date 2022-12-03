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
import dev.hypest.pis.restaurants.domain.products.DeleteProductHandler
import dev.hypest.pis.restaurants.domain.products.UpdateProductHandler
import dev.hypest.pis.restaurants.domain.restaurants.CreateRestaurantHandler
import dev.hypest.pis.restaurants.domain.restaurants.DeleteRestaurantHandler
import dev.hypest.pis.restaurants.domain.restaurants.UpdateRestaurantHandler
import dev.hypest.pis.restaurants.product.CreateProductRequest
import dev.hypest.pis.restaurants.product.ProductResponse
import dev.hypest.pis.restaurants.product.UpdateProductRequest
import dev.hypest.pis.restaurants.restaurants.CreateRestaurantRequest
import dev.hypest.pis.restaurants.restaurants.RestaurantResponse
import dev.hypest.pis.restaurants.restaurants.RestaurantsApi
import dev.hypest.pis.restaurants.restaurants.UpdateRestaurantRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import java.util.UUID

@Suppress("LongParameterList")
@Controller("/restaurants")
class RestaurantsController(
    private val createRestaurantHandler: CreateRestaurantHandler,
    private val updateRestaurantHandler: UpdateRestaurantHandler,
    private val deleteRestaurantHandler: DeleteRestaurantHandler,
    private val createProductHandler: CreateProductHandler,
    private val updateProductHandler: UpdateProductHandler,
    private val deleteProductHandler: DeleteProductHandler,
    private val restaurantQuery: RestaurantQuery,
    private val productQuery: ProductQuery
) : RestaurantsApi {
    override fun getAllRestaurants(): HttpResponse<List<RestaurantResponse>> =
        restaurantQuery.getRestaurants().let { HttpResponse.ok(it) }

    override fun getRestaurantById(restaurantId: UUID): HttpResponse<RestaurantResponse> =
        restaurantQuery.getRestaurant(restaurantId).let { HttpResponse.ok(it) }

    override fun createRestaurant(restaurant: CreateRestaurantRequest): HttpResponse<UuidWrapper> {
        val command = mapToCreateRestaurantCommand(restaurant)
        val id = createRestaurantHandler.create(command)
        return HttpResponse.created(UuidWrapper(id))
    }

    override fun deleteRestaurant(restaurantId: UUID): HttpResponse<Unit> {
        deleteRestaurantHandler.delete(restaurantId)
        return HttpResponse.ok()
    }

    override fun updateRestaurant(restaurantId: UUID, request: UpdateRestaurantRequest): HttpResponse<UuidWrapper> {
        val command = mapToUpdateRestaurantCommand(restaurantId, request)
        val id = updateRestaurantHandler.update(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    override fun addProductToRestaurant(restaurantId: UUID, request: CreateProductRequest): HttpResponse<UuidWrapper> {
        val command = mapToCreateProductCommand(restaurantId, request)
        val id = createProductHandler.create(command)
        return HttpResponse.created(UuidWrapper(id))
    }

    override fun getProductsFromRestaurant(restaurantId: UUID): HttpResponse<List<ProductResponse>> =
        productQuery.getProductsFromRestaurant(restaurantId).let { HttpResponse.ok(it) }

    override fun getProductFromRestaurant(restaurantId: UUID, productId: UUID): HttpResponse<ProductResponse> =
        productQuery.getProduct(restaurantId, productId).let { HttpResponse.ok(it) }

    override fun updateProductFromRestaurant(
        restaurantId: UUID,
        productId: UUID,
        request: UpdateProductRequest
    ): HttpResponse<UuidWrapper> {
        val command = mapToUpdateProductCommand(productId, request)
        val id = updateProductHandler.update(command)
        return HttpResponse.ok(UuidWrapper(id))
    }

    override fun deleteProductFromRestaurant(restaurantId: UUID, productId: UUID): HttpResponse<Unit> {
        deleteProductHandler.delete(productId)
        return HttpResponse.ok()
    }
}
