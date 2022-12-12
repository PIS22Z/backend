package dev.hypest.pis.payments.domain.ordertopay

import java.util.UUID

class OrderToPayNotFoundException(orderId: UUID) :
    NoSuchElementException("Order to pay with id $orderId not found")
