package br.com.caju.transaction.domain.usecase.wallet

import br.com.caju.transaction.adapter.dataprovider.WalletDataProvider
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.dto.WalletDomain
import org.springframework.stereotype.Component

@Component
class FindByTransactionTypeAndAccountNumberUseCase (
    private val walletDataProvider: WalletDataProvider
){
    fun execute(transactionType: TransactionType, accountDomain: TransactionDomain): WalletDomain{
        return walletDataProvider.findByTransactionTypeAndAccountNumber(
            transactionType = transactionType,
            account = accountDomain.account!!.number
        )
    }
}