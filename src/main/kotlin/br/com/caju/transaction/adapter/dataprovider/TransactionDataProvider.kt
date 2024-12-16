package br.com.caju.transaction.adapter.dataprovider

import br.com.caju.transaction.core.repository.TransactionRepository
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.adapter.converter.TransactionConverter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TransactionDataProvider(
        private val repository: TransactionRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun save(transaction: TransactionDomain): TransactionDomain {
        log.info("m=save, step=initial")
        val response = repository.save(TransactionConverter.toEntity(transaction))
                .also { log.info("m=save, step=initial, transactionCode={}", it.transactionCode)  }

        return TransactionConverter.toDomain(response)
    }
}