package dev.hypest.pis.restaurants.adapter.in.rest

import dev.hypest.pis.BaseTest
import dev.hypest.pis.infrastructure.db.DbRestaurantQuery
import dev.hypest.pis.restaurants.adapter.in.query.RestaurantQuery
import dev.hypest.pis.restaurants.domain.products.*
import dev.hypest.pis.restaurants.domain.restaurants.*
import dev.hypest.pis.restaurants.product.CreateProductRequest
import dev.hypest.pis.restaurants.product.UpdateProductRequest
import dev.hypest.pis.restaurants.restaurants.CreateRestaurantRequest
import dev.hypest.pis.restaurants.restaurants.UpdateRestaurantRequest
import io.micronaut.http.HttpStatus
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject

class RestaurantControllerTest extends BaseTest {

    @Inject
    CreateRestaurantHandler createRestaurantHandler

    @Inject
    UpdateRestaurantHandler updateRestaurantHandler

    @Inject
    CreateRestaurantHandler getRestaurantHandler

    @Inject
    DeleteRestaurantHandler deleteRestaurantHandler

    @Inject
    CreateProductHandler createProductHandler

    @Inject
    UpdateProductHandler updateProductHandler

    @Inject
    DeleteProductHandler deleteProductHandler

    @Inject
    RestaurantQuery restaurantQuery

    @Inject
    private RestaurantClient client

    def "when post is performed against /restaurants"() {
        given:
        CreateRestaurantCommand command

        when:
        def request = new CreateRestaurantRequest(
                "name",
                "url",
                "description",
                "city",
                "street",
                "number"
        )
        def response = client.createRestaurant(request)

        then:
        1 * createRestaurantHandler.create(_ as CreateRestaurantCommand) >> { List<CreateRestaurantCommand> args ->
            command = args[0]
            UUID.randomUUID()
        }

        command != null
        command.name == request.name
        command.logoUrl == request.logoUrl
        command.description == request.description
        command.city == request.city

        response != null
        response.status == HttpStatus.CREATED
        response.body().id != null
    }

    def "when put is performed against /restaurants/{restaurantId}"() {
        given:
        UpdateRestaurantCommand command
        def restaurantId = UUID.randomUUID()

        when:
        def request = new UpdateRestaurantRequest(
                "name",
                "url",
                "description",
                "city",
                "street",
                "number"
        )
        def response = client.updateRestaurant(restaurantId, request)

        then:
        1 * updateRestaurantHandler.update(_ as UpdateRestaurantCommand) >> { List<UpdateRestaurantCommand> args ->
            command = args[0]
            restaurantId
        }

        command != null
        command.name == request.name
        command.logoUrl == request.logoUrl
        command.description == request.description
        command.city == request.city

        response != null
        response.status == HttpStatus.OK
        response.body().id != null
    }

    def "when delete is performed against /restaurants/{restaurantId}"() {
        given:
        UUID restaurantId = UUID.randomUUID()

        when:
        def response = client.deleteRestaurant(restaurantId)

        then:
        1 * deleteRestaurantHandler.delete(_ as UUID) >> { List<UUID> args ->
            restaurantId == args[0]
        }

        response != null
        response.status == HttpStatus.OK
    }

    def "when post is performed against /restaurants/{restaurantId}/products"() {
        given:
        CreateProductCommand command

        when:
        def request = new CreateProductRequest(
                "name",
                "url",
                1.0,
        )
        def response = client.addProductToRestaurant(UUID.randomUUID(), request)

        then:
        1 * createProductHandler.create(_ as CreateProductCommand) >> { List<CreateProductCommand> args ->
            command = args[0]
            UUID.randomUUID()
        }

        command != null
        command.name == request.name
        command.photoUrl == request.photoUrl

        response != null
        response.status == HttpStatus.CREATED
        response.body().id != null
    }

    def "when put is performed against /restaurants/{restaurant}/products/{productId}"() {
        given:
        UpdateProductCommand command
        def restaurantId = UUID.randomUUID()
        def productId = UUID.randomUUID()

        when:
        def request = new UpdateProductRequest(
                "name",
                "url",
                1.0
        )

        def response = client.updateProductFromRestaurant(restaurantId, productId, request)

        then:
        1 * updateProductHandler.update(_ as UpdateProductCommand) >> { List<UpdateProductCommand> args ->
            command = args[0]
            productId
        }

        command != null
        command.name == request.name
        command.photoUrl == request.photoUrl


        response != null
        response.status == HttpStatus.OK
        response.body().id != null
    }

    def "when delete is performed against /restaurants/{restaurant}/products"() {
        given:
        def productId = UUID.randomUUID()

        when:
        def response = client.deleteProductFromRestaurant(UUID.randomUUID(), productId)

        then:
        1 * deleteProductHandler.delete(_ as UUID) >> { List<UUID> args ->
            args[0] == productId
        }

        response != null
        response.status == HttpStatus.OK
    }

    @MockBean(CreateRestaurantHandlerImpl)
    CreateRestaurantHandler createRestaurantHandler() {
        Mock(CreateRestaurantHandler)
    }

    @MockBean(UpdateRestaurantHandlerImpl)
    UpdateRestaurantHandler updateRestaurantHandler() {
        Mock(UpdateRestaurantHandler)
    }

    @MockBean(DeleteRestaurantHandlerImpl)
    DeleteRestaurantHandler deleteRestaurantHandler() {
        Mock(DeleteRestaurantHandler)
    }

    @MockBean(CreateProductHandlerImpl)
    CreateProductHandler createProductHandler() {
        Mock(CreateProductHandler)
    }

    @MockBean(UpdateProductHandlerImpl)
    UpdateProductHandler updateProductHandler() {
        Mock(UpdateProductHandler)
    }

    @MockBean(DeleteProductHandlerImpl)
    DeleteProductHandler deleteProductHandler() {
        Mock(DeleteProductHandler)
    }

    @MockBean(DbRestaurantQuery)
    RestaurantQuery restaurantQuery() {
        Mock(RestaurantQuery)
    }
}
