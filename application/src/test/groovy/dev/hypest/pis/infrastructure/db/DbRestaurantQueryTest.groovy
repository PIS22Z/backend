package dev.hypest.pis.infrastructure.db

import dev.hypest.pis.BaseTest
import dev.hypest.pis.restaurants.adapter.in.query.RestaurantQuery
import dev.hypest.pis.restaurants.infrastructure.db.products.MicronautDataProductRepository
import dev.hypest.pis.restaurants.infrastructure.db.products.ProductEntity
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.MicronautDataRestaurantRepository
import dev.hypest.pis.restaurants.infrastructure.db.restaurant.RestaurantEntity
import jakarta.inject.Inject

class DbRestaurantQueryTest extends BaseTest {

    @Inject
    private RestaurantQuery restaurantQuery

    @Inject
    private MicronautDataRestaurantRepository restaurantRepository

    @Inject
    private MicronautDataProductRepository productRepository

    def "given existing restaurant, when restaurant is queried, then it should return restaurant"() {
        given:
        def existingRestaurant = restaurantRepository.save(new RestaurantEntity(UUID.randomUUID(),
                "name",
                "url",
                "description",
                [],
                "city",
                "street",
                "number"))

        when:
        def restaurant = restaurantQuery.getRestaurant(existingRestaurant.id)

        then:
        restaurant.id == existingRestaurant.id
        restaurant.name == existingRestaurant.name
        restaurant.logoUrl == existingRestaurant.logoUrl
        restaurant.description == existingRestaurant.description
        restaurant.city == existingRestaurant.city
        restaurant.street == existingRestaurant.street
        restaurant.number == existingRestaurant.number
    }

    def "given restaurant with many products, when restaurants are queried, then it should return only first products"() {
        def restaurantId = UUID.randomUUID()
        def existingRestaurant = new RestaurantEntity(
                restaurantId,
                "name",
                "url",
                "description",
                ([{ getProductEntity() }] * 10)*.call(),
                "city",
                "street",
                "number")
        restaurantRepository.save(existingRestaurant)

        when:
        def restaurant = restaurantQuery.getRestaurants()

        then:
        restaurant.size() == 1
        restaurant[0].products.size() == DbRestaurantQuery.PRODUCTS_COUNT_FIND_ALL
        restaurant[0].products[0..DbRestaurantQuery.PRODUCTS_COUNT_FIND_ALL - 1].eachWithIndex { it, index -> restaurant[0].products[index].id == it.id }
    }

    def "given many restaurants, when restaurants are queried, then it should return all restaurants"() {
        def existingRestaurants = ([{ getRestaurantEntity() }] * 10)*.call()
        restaurantRepository.saveAll(existingRestaurants)

        when:
        def restaurants = restaurantQuery.getRestaurants()

        then:
        restaurants.size() == existingRestaurants.size()
        existingRestaurants.eachWithIndex { it, index -> restaurants[index].id == it.id }
    }

    def "given existing restaurant, when restaurant is queried, then it should return restaurant with all products"() {
        def restaurantId = UUID.randomUUID()
        def existingRestaurant = new RestaurantEntity(restaurantId,
                "name",
                "url",
                "description",
                ([{ getProductEntity() }] * 10)*.call(),
                "city",
                "street",
                "number")
        restaurantRepository.save(existingRestaurant)

        when:
        def restaurant = restaurantQuery.getRestaurant(restaurantId)

        then:
        restaurant.products.size() == existingRestaurant.products.size()
    }

    static getRestaurantEntity(Map map = [:]) {
        new RestaurantEntity(
                map.id ?: UUID.randomUUID(),
                map.name ?: "name",
                map.logoUrl ?: "url",
                map.description ?: "description",
                map.products ?: [],
                map.city ?: "city",
                map.street ?: "street",
                map.number ?: "number",
        )
    }

    static getProductEntity(Map map = [:]) {
        new ProductEntity(
                map.id ?: UUID.randomUUID(),
                map.restaurantId ?: UUID.randomUUID(),
                map.name ?: "name",
                map.logoUrl ?: "url",
                map.price ?: BigDecimal.ZERO,
        )
    }
}
