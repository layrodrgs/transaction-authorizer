package br.com.caju.transaction.domain.usecase.merchant

import br.com.caju.transaction.adapter.dataprovider.MerchantDataProvider
import br.com.caju.transaction.domain.dto.MerchantDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetMerchantByNameOrMccUseCase (
    private val merchantDataProvider: MerchantDataProvider
){
    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(mcc: String? = null, name: String? = null): MerchantDomain?{
        log.info("m=execute, step=initial")
        return merchantDataProvider.findByMccOrName(mcc, name)
            .also { log.info("m=execute, step=end, merchant={}", it?.mcc) }
    }
}