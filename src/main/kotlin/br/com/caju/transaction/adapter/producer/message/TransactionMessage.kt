package br.com.caju.transaction.adapter.producer.message

import java.math.BigDecimal

class TransactionMessage(
        val totalAmount: BigDecimal,
        val type: String,
        val mcc: String,
        val merchant: String,
        val account: String? = null
)