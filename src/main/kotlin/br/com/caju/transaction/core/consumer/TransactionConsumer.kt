package br.com.caju.transaction.core.consumer

import br.com.caju.transaction.core.consumer.message.TransactionMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class TransactionConsumer(

) {
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["transaction"])
    fun startConsumer(message: TransactionMessage){
        log.info("M=startConsumer, step=initial, params={}", message)
    }
}