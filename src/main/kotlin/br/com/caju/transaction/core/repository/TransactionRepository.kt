package br.com.caju.transaction.core.repository

import br.com.caju.transaction.core.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository


interface TransactionRepository: JpaRepository<Transaction, Long> {
}