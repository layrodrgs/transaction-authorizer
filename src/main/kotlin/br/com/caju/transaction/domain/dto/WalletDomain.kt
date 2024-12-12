package br.com.caju.transaction.domain.dto

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

class WalletDomain(
        val type: TransactionType? = null,
        val amount: BigDecimal? = null,
        val account: AccountdDomain? = null
)