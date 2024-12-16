package br.com.caju.transaction.domain.usecase.wallet

import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.dto.WalletDomain
import br.com.caju.transaction.domain.exception.InsuficientAmountException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class VerifyAmountUseCase (
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(walletDomain: WalletDomain, transactionDomain: TransactionDomain){
        if(walletDomain.amount!! < transactionDomain.amount){
            log.info("m=execute, step=value not sufficient, account={}, transactionType={}", transactionDomain.account, transactionDomain)
            throw InsuficientAmountException("The ${walletDomain.type} has no sufficient amount")
        }
    }
}