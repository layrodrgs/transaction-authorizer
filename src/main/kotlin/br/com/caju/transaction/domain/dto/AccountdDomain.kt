package br.com.caju.transaction.domain.dto

class AccountdDomain(
        val id: Long? = null,
        val number: String,
        val wallets: List<WalletDomain>? = null
)