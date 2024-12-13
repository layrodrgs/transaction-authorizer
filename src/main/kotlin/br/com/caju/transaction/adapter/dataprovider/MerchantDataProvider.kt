package br.com.caju.transaction.adapter.dataprovider

import br.com.caju.transaction.adapter.converter.MerchantConverter
import br.com.caju.transaction.core.repository.MerchantRepository
import br.com.caju.transaction.domain.dto.MerchantDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MerchantDataProvider(
    private val merchantRepository: MerchantRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun findByMccOrName(mcc: String? = null, name: String? = null): MerchantDomain?{
        log.info("m=findByMccOrName, step=initial")
        return merchantRepository.findByMccOrName(mcc, name)
            .let { MerchantConverter.toDomain(it) }
            .also {  log.info("m=findByMccOrName, step=initial, merchant={}", it.name) }
    }
}