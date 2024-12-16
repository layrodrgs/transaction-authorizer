package br.com.caju.transaction.domain.dto

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal
import java.util.*

data class TransactionDomain(
        val amount: BigDecimal,
        val type: TransactionType,
        val transactionCode: String = UUID.randomUUID().toString(),
        val account: AccountdDomain?,
        val wallet: WalletDomain? = null,
        val merchant: MerchantDomain,
        val step: String = "START"
)