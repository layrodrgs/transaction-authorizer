package br.com.caju.transaction.domain.dto

class AccountdDomain(
        val number: String,
        val wallets: List<WalletDomain>? = null
)