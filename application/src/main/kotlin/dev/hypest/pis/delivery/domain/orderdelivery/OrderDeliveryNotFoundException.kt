package dev.hypest.pis.delivery.domain.orderdelivery

import java.util.UUID

class OrderDeliveryNotFoundException(orderId: UUID) :
    NoSuchElementException("Order delivery with id $orderId not found")
