package br.com.caju.transaction.core.repository

import br.com.caju.transaction.core.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository


interface WalletRepository: JpaRepository<Wallet, Long> {
}