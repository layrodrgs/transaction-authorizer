package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.factory.Factory
import br.com.caju.transaction.domain.usecase.merchant.GetMerchantByNameOrMccUseCase
import br.com.caju.transaction.domain.usecase.wallet.WalletSubtractUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProcessTransactionUseCase(
    private val walletSubstractUseCase: WalletSubtractUseCase
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain){
        log.info("m=execute, step=initial ")
        walletSubstractUseCase.execute(transactionDomain = transactionDomain, transactionType = transactionDomain.type)
    }
}