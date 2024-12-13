package br.com.caju.transaction.domain.strategy

import br.com.caju.transaction.domain.dto.TransactionDomain

interface TransactionStrategy {
    fun process(transactionDomain: TransactionDomain)
    fun equalsMcc(mcc: String?): Boolean
}