package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.adapter.converter.TransactionConverter
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.adapter.dataprovider.TransactionDataProvider
import br.com.caju.transaction.adapter.producer.RabbitMqProducerAdapter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CreateTransactionUseCase (
        private val transactionDataProvider: TransactionDataProvider
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain): TransactionDomain{
        log.info("m=execute, step=initial")

        return transactionDataProvider.save(transactionDomain)
                .also { log.info("m=execute, step=initial, transactionCode={}", it.transactionCode) }
    }
}