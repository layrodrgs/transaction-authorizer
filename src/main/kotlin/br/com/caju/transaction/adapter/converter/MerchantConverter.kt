package br.com.caju.transaction.adapter.converter

import br.com.caju.transaction.core.entity.Merchant
import br.com.caju.transaction.domain.dto.MerchantDomain


class MerchantConverter {
    companion object {
        fun toDomain(merchant: Merchant?): MerchantDomain {
            return MerchantDomain(
                mcc = merchant?.mcc,
                name = merchant?.name
            )
        }
    }
}