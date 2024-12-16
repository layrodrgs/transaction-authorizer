package br.com.caju.transaction.domain.strategy

import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.dto.WalletDomain

interface TransactionStrategy {
    fun process(transactionDomain: TransactionDomain): WalletDomain
    fun equalsMcc(mcc: String?): Boolean
}