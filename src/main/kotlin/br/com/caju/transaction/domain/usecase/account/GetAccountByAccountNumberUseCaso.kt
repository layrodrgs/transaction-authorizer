package br.com.caju.transaction.domain.usecase.account

import br.com.caju.transaction.adapter.dataprovider.AccountDataProvider
import br.com.caju.transaction.domain.dto.AccountdDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetAccountByAccountNumberUseCaso(
    private val accountDataProvider: AccountDataProvider
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(number: String): AccountdDomain? {
        log.info("m=findByAccountNumber, step=initial, accountNumber={}", number)

        return accountDataProvider.findByAccountNumber(accountNumber = number)
            .also { log.info("m=findByAccountNumber, step=initial, accountNumber={}", number) }
    }
}