package br.com.caju.transaction.adapter.dataprovider

import br.com.caju.transaction.adapter.converter.WalletConverter
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.core.repository.WalletRepository
import br.com.caju.transaction.domain.dto.WalletDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class WalletDataProvider(
    private val walletRepository: WalletRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findByTransactionTypeAndAccountNumber(account: String, transactionType: TransactionType): WalletDomain{
        log.info("m=findByMccOrName, step=initial")

        return walletRepository.findByTypeAndAccountNumber(transactionType, account)
            .let { WalletConverter.toDomain(it) }
            .also { log.info("m=findByMccOrName, step=end, account={}, type={}, amount={}", it.account, it.type, it.amount) }
    }

    fun save(save: WalletDomain): WalletDomain{
        log.info("m=save, step=initial")

        return walletRepository.save(WalletConverter.toEntity(save))
            .let { WalletConverter.toDomain(it) }
            .also { log.info("m=save, step=end, account={}, type={}, amount={}", it.account, it.type, it.amount) }
    }
}