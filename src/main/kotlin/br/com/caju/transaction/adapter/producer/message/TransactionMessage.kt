package br.com.caju.transaction.adapter.producer.message

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

class TransactionMessage(
        val totalAmount: BigDecimal,
        val type: TransactionType? = null,
        val mcc: String,
        val merchant: String,
        val account: String? = null
)