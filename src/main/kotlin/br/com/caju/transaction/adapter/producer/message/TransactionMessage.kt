package br.com.caju.transaction.adapter.producer.message

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

class TransactionMessage(
        val totalAmount: BigDecimal,
        val type: TransactionType? = null,
        val merchant: MerchantMessage,
        val account: AccountMessage? = null
)