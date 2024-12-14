package br.com.caju.transaction.core.repository

import br.com.caju.transaction.core.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long> {
    fun findByNumber(number: String): Account?
}