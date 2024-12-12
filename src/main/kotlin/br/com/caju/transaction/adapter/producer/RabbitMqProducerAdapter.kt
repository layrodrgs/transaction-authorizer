package br.com.caju.transaction.adapter.producer

import br.com.caju.transaction.core.producer.RabbitMqProducer
import org.springframework.stereotype.Component

@Component
class RabbitMqProducerAdapter(
        private val rabbitMqProducer: RabbitMqProducer
) {
    fun sendToQueueTransaction(payload: Any){
        rabbitMqProducer.sendToQueue(routingKey = "creation", exchange = "transaction", payload = payload)
    }
}