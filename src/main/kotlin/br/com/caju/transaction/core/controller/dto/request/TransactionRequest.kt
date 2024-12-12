package br.com.caju.transaction.core.controller.dto.request

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import java.math.BigDecimal

class TransactionRequest(
        val totalAmount: BigDecimal,
        val type: TransactionType,
        val mcc: String,
        val merchant: String,
        val account: String
)