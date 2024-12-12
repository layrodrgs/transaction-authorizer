package br.com.caju.transaction.core.entity

import br.com.caju.transaction.core.entity.enumerated.TransactionType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_wallet")
class Wallet(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val type: TransactionType,
        val amount: BigDecimal,
        @ManyToOne
        val account: Account
)