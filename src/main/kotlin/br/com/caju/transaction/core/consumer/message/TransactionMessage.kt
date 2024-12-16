package br.com.caju.transaction.core.consumer.message

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

class TransactionMessage (
        val totalAmount: BigDecimal,
        val type: TransactionType,
        val mcc: String,
        val merchant: String,
        val account: String
)