package br.com.caju.transaction.core.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_merchant")
class Merchant(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val mcc: String,
        val name: String? = null
)