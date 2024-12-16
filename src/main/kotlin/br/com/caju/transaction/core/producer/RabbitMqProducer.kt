package br.com.caju.transaction.core.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMqProducer(
        val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendToQueue(routingKey: String, exchange: String, payload: Any){
        log.info("m=sendToQueue, step=initial")
        rabbitTemplate.convertAndSend(exchange, routingKey, payload)
                .also { log.info("m=sendToQueue, step=end") }
    }
}