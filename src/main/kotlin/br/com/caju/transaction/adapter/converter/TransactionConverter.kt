package br.com.caju.transaction.adapter.converter

import br.com.caju.transaction.adapter.producer.message.TransactionMessage
import br.com.caju.transaction.core.controller.dto.request.TransactionRequest
import br.com.caju.transaction.core.entity.Account
import br.com.caju.transaction.core.entity.Merchant
import br.com.caju.transaction.core.entity.Transaction
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.dto.MerchantDomain
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.dto.WalletDomain

class TransactionConverter {
    companion object {
        fun toDomain(transaction: Transaction): TransactionDomain{
            val account = transaction.account?.let {
                AccountdDomain(number = it.number) }

            return TransactionDomain(
                    amount = transaction.amount,
                    type = transaction.type,
                    merchant = MerchantDomain(mcc = transaction.merchant?.mcc),
                    account = account,
                    wallet = WalletDomain(type = transaction.wallet?.type, account = account)
            )
        }

        fun toEntity(transaction: TransactionDomain): Transaction{
            return Transaction(
                    amount = transaction.amount,
                    type = transaction.type,
            )
        }

        fun requestToDomain(transaction: TransactionRequest): TransactionDomain{
            return TransactionDomain(
                    amount = transaction.totalAmount,
                    type = transaction.type,
                    account = AccountdDomain(number =  transaction.account),
                    merchant = MerchantDomain(mcc = transaction.mcc)
            )
        }


        fun domainToMessage(transaction: TransactionDomain): TransactionMessage {
            return TransactionMessage(
                    totalAmount = transaction.amount,
                    type = transaction.type.toString(),
                    merchant = (transaction.merchant.name ?: transaction.merchant.mcc)!!,
                    mcc = transaction.merchant.mcc!!,
                    account = transaction.account?.number
            )
        }
    }
}