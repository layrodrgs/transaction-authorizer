package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.factory.Factory
import br.com.caju.transaction.domain.usecase.merchant.GetMerchantByNameOrMccUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProcessTransactionUseCase(
    private val factory: Factory,
    private val merchantByNameOrMccUseCase: GetMerchantByNameOrMccUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain){
        log.info("m=execute, step=initial ")
        val merchant = merchantByNameOrMccUseCase.execute(transactionDomain.merchant.mcc,
            transactionDomain.merchant.name)

        factory.getStrategyByMcc(merchant?.mcc)
                .process(transactionDomain)
                .also { createTransactionUseCase.execute(createTransactionDomain(transactionDomain)) }
                .also { log.info("m=execute, step=end ") }
    }

    private fun createTransactionDomain(transactionDomain: TransactionDomain): TransactionDomain{
        return transactionDomain.copy(step = "END")
    }
}