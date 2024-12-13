package br.com.caju.transaction.domain.dto

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

data class WalletDomain(
        val id : Long ? = null,
        val type: TransactionType? = null,
        val amount: BigDecimal? = null,
        val account: AccountdDomain? = null
)