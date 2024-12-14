package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.adapter.converter.AccountConverter
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.adapter.dataprovider.TransactionDataProvider
import br.com.caju.transaction.domain.exception.AccountNotFoundException
import br.com.caju.transaction.domain.usecase.account.GetAccountByAccountNumberUseCaso
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CreateTransactionUseCase (
        private val transactionDataProvider: TransactionDataProvider,
        private val accountNumberUseCaso: GetAccountByAccountNumberUseCaso
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain): TransactionDomain{
        log.info("m=execute, step=initial")
        val account = transactionDomain.account?.number?.let { accountNumberUseCaso.execute(it) }
            ?: throw AccountNotFoundException("The current account was not found.")

        transactionDataProvider.save(transactionDomain)
                .also { log.info("m=execute, step=initial, transactionCode={}", it.transactionCode) }
        
        return transactionDomain.copy(account = account)
    }
}