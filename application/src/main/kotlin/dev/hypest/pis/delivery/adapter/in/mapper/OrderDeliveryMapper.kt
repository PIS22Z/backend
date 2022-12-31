@file:Suppress("InvalidPackageDeclaration")

package dev.hypest.pis.delivery.adapter.`in`.mapper

import dev.hypest.pis.delivery.AcceptOrderDeliveryRequest
import dev.hypest.pis.delivery.domain.orderdelivery.AcceptOrderDeliveryCommand
import dev.hypest.pis.delivery.domain.orderdelivery.StartOrderDeliveryCommand
import java.util.UUID

object OrderDeliveryMapper {

    fun mapToAcceptOrderDeliveryCommand(
        orderDeliveryId: UUID,
        request: AcceptOrderDeliveryRequest
    ): AcceptOrderDeliveryCommand {
        return AcceptOrderDeliveryCommand(orderDeliveryId, request.courierId)
    }

    fun mapToStartOrderDeliveryCommand(
        orderDeliveryId: UUID
    ): StartOrderDeliveryCommand {
        return StartOrderDeliveryCommand(orderDeliveryId)
    }
}
