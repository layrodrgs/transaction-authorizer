package br.com.caju.transaction.core.entity

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "tb_transaction")
class Transaction(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val amount: BigDecimal,
        val type: TransactionType,
        val transactionCode: String = UUID.randomUUID().toString(),
        val creationDate: LocalDateTime = LocalDateTime.now(),
        @OneToOne
        val account: Account? = null,
        @OneToOne
        val wallet: Wallet? = null,
        @OneToOne
        val merchant: Merchant ? = null
)