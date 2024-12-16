package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.adapter.converter.TransactionConverter
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.adapter.producer.RabbitMqProducerAdapter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SendTransactionQueueUseCase (
        private val rabbitMqProducerAdapter: RabbitMqProducerAdapter,
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain){
        log.info("m=execute, message=sending transaction to queue, step=end")
        rabbitMqProducerAdapter.sendToQueueTransaction(TransactionConverter.domainToMessage(transactionDomain))
            .also { log.info("m=execute, message=sending transaction to queue, step=end") }
    }
}