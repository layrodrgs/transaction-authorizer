package br.com.caju.transaction.core.repository

import br.com.caju.transaction.core.entity.Wallet
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository: JpaRepository<Wallet, Long> {
    fun findByTypeAndAccountNumber(transactionType: TransactionType, account: String): Wallet
}