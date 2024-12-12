package br.com.caju.transaction.core.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_account")
class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val number: String,
        @OneToMany
        val wallets: List<Wallet>? = null
)