package br.com.caju.transaction.domain.usecase.wallet

import br.com.caju.transaction.adapter.dataprovider.WalletDataProvider
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.TransactionDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WalletSubtractUseCase(
    private val walletDataProvider: WalletDataProvider
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain, transactionType: TransactionType) {
        log.info("m=execute, step=initial, account={}, transactionType={}", transactionDomain.account, transactionDomain)
        val wallet = walletDataProvider.findByTransactionTypeAndAccountNumber(
            transactionType = transactionType,
            account = transactionDomain.account!!.number
        )

        if(wallet.amount!! < transactionDomain.amount){
            log.info("m=execute, step=value not sufficient, account={}, transactionType={}", transactionDomain.account, transactionDomain)
            return
        }

        val substract = wallet.amount.subtract(transactionDomain.amount)

        val save = wallet.copy(
            amount = substract
        )

        walletDataProvider.save(save)
            .also { log.info("m=execute, step=end account={}, transactionType={}", transactionDomain.account, transactionDomain) }
    }
}