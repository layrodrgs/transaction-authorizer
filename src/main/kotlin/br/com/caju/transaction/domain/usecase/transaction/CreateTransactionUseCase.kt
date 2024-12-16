package br.com.caju.transaction.domain.usecase.transaction

import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.adapter.dataprovider.TransactionDataProvider
import br.com.caju.transaction.domain.exception.AccountNotFoundException
import br.com.caju.transaction.domain.exception.MerchantNotFoundException
import br.com.caju.transaction.domain.factory.Factory
import br.com.caju.transaction.domain.usecase.account.GetAccountByAccountNumberUseCaso
import br.com.caju.transaction.domain.usecase.merchant.GetMerchantByNameOrMccUseCase
import br.com.caju.transaction.domain.usecase.wallet.VerifyAmountUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CreateTransactionUseCase (
    private val transactionDataProvider: TransactionDataProvider,
    private val accountNumberUseCaso: GetAccountByAccountNumberUseCaso,
    private val merchantByNameOrMccUseCase: GetMerchantByNameOrMccUseCase,
    private val verifyAmountUseCase: VerifyAmountUseCase,
    private val factory: Factory
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(transactionDomain: TransactionDomain): TransactionDomain{
        log.info("m=execute, step=initial")
        val account = transactionDomain.account?.number?.let { accountNumberUseCaso.execute(it) }
            ?: throw AccountNotFoundException("The current account was not found.")

        val merchant = merchantByNameOrMccUseCase.execute(transactionDomain.merchant.mcc,
            transactionDomain.merchant.name) ?: throw MerchantNotFoundException("The current merchant was not found.")

        val wallet = factory.getStrategyByMcc(transactionDomain.merchant.mcc)
            .process(transactionDomain)

        verifyAmountUseCase.execute(wallet, transactionDomain)

        val toSave = transactionDomain.copy(
            account = account.copy(wallets = listOf(wallet)),
            merchant = merchant,
            type = wallet.type!!
        )
        transactionDataProvider.save(transactionDomain)
                .also { log.info("m=execute, step=initial, transactionCode={}", it.transactionCode) }

        return toSave
    }
}