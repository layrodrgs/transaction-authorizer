package br.com.caju.transaction.adapter.converter

import br.com.caju.transaction.core.entity.Account
import br.com.caju.transaction.domain.dto.AccountdDomain


class AccountConverter {
    companion object {
        fun toDomain(account: Account?): AccountdDomain? {
            return account?.number?.let {
                AccountdDomain(
                    number = it,
                    id = account.id
                )
            }
        }
    }
}