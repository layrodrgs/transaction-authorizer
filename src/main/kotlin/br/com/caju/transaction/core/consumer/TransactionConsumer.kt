package br.com.caju.transaction.core.consumer

import br.com.caju.transaction.adapter.converter.TransactionConverter
import br.com.caju.transaction.adapter.producer.message.TransactionMessage
import br.com.caju.transaction.domain.usecase.transaction.ProcessTransactionUseCase
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class TransactionConsumer(
    private val processTransactionUseCase: ProcessTransactionUseCase
) {
    private val log = LoggerFactory.getLogger(javaClass)
    @RabbitListener(queues = ["transaction.queue"])
    fun startConsumer(message: TransactionMessage){
        log.info("M=startConsumer, step=initial, params={}", message)
        processTransactionUseCase.execute(TransactionConverter.messageToDomain(message))
            .also { log.info("M=startConsumer, step=end, params={}", message) }
    }
}