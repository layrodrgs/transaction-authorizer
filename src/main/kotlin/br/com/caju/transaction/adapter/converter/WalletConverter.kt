package br.com.caju.transaction.adapter.converter

import br.com.caju.transaction.core.entity.Account
import br.com.caju.transaction.core.entity.Wallet
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.dto.WalletDomain


class WalletConverter {
    companion object {
        fun toDomain(wallet: Wallet): WalletDomain {
            return WalletDomain(
                id = wallet.id,
                account = wallet.account?.let { AccountdDomain(id = it.id, number =  it.number) },
                amount = wallet.amount,
                type = wallet.type
            )
        }

        fun toEntity(wallet: WalletDomain): Wallet {
            return Wallet(
                id = wallet.id,
                account = wallet.account?.let { Account(id = it.id, number = it.number) },
                amount = wallet.amount!!,
                type = wallet.type!!
            )
        }
    }
}