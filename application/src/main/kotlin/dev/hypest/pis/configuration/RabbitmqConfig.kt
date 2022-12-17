package dev.hypest.pis.configuration

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import io.micronaut.rabbitmq.connect.ChannelInitializer
import jakarta.inject.Singleton

@Singleton
class RabbitmqConfig : ChannelInitializer() {

    override fun initialize(channel: Channel, name: String) {
        // exchange + queues
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT) // TODO direct?
        channel.queueDeclare(ORDER_FINALIZED, false, false, false, emptyMap())
        channel.queueDeclare(ORDER_PAID, false, false, false, emptyMap())
        channel.queueDeclare(ORDER_READY, false, false, false, emptyMap())

        // queues bindings
        channel.queueBind(ORDER_FINALIZED, EXCHANGE, ORDER_FINALIZED)
        channel.queueBind(ORDER_PAID, EXCHANGE, ORDER_PAID)
    }

    companion object {
        const val EXCHANGE = "pis"
        const val ORDER_FINALIZED = "order-finalized"
        const val ORDER_PAID = "order-paid"
        const val ORDER_READY = "order-ready"
    }
}
