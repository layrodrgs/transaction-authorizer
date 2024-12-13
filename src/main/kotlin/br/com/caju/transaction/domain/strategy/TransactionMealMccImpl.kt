package br.com.caju.transaction.domain.strategy

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.usecase.wallet.WalletSubtractUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class TransactionMealMccImpl(
    private val walletSubtractUseCase: WalletSubtractUseCase

): TransactionStrategy {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun process(transactionDomain: TransactionDomain) {
        log.info("m=process, step=initial, type=MEAL, merchant={}, transactionCode={}",
            transactionDomain.merchant, transactionDomain.transactionCode)

        walletSubtractUseCase.execute(transactionDomain = transactionDomain, transactionType = TransactionType.MEAL)
    }

    override fun equalsMcc(mcc: String?): Boolean {
        return mcc in listOf("5811", "5812")
    }
}