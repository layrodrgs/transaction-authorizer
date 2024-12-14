package br.com.caju.transaction.adapter.dataprovider

import br.com.caju.transaction.adapter.converter.AccountConverter
import br.com.caju.transaction.core.repository.AccountRepository
import br.com.caju.transaction.domain.dto.AccountdDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AccountDataProvider(
    private val repository: AccountRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findByAccountNumber(accountNumber: String): AccountdDomain?{
        log.info("m=findByAccountNumber, step=initial, accountNumber={}", accountNumber)
        return repository.findByNumber(number = accountNumber)
            .let { AccountConverter.toDomain(it) }
            ?.also {  log.info("m=findByAccountNumber, step=end, accountNumber={}", it.number) }
    }
}