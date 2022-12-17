@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.delivery.adapter.`in`.query

import dev.hypest.pis.delivery.OrderDeliveryOfferResponse

interface OrderDeliveryOfferQuery {

    fun getOrderDeliveryOffer(courierAddress: String): OrderDeliveryOfferResponse?
}
