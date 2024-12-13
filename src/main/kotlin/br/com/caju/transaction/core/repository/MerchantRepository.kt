package br.com.caju.transaction.core.repository

import org.springframework.data.jpa.repository.JpaRepository
import br.com.caju.transaction.core.entity.Merchant

interface MerchantRepository: JpaRepository<Merchant, Long> {
    fun findByMccOrName(mcc: String? = null, name: String? = null): Merchant?
}