package br.com.caju.transaction.domain.factory

import br.com.caju.transaction.domain.strategy.TransactionCashMccImpl
import br.com.caju.transaction.domain.strategy.TransactionStrategy
import org.springframework.stereotype.Component

@Component
class Factory(
        private val transactionStrategys: List<TransactionStrategy>
) {
    fun getStrategyByMcc(mcc: String? = null): TransactionStrategy {
        return transactionStrategys.firstOrNull { it.equalsMcc(mcc) }!!
    }
}