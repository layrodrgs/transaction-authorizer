package domain.usecase.wallet

import br.com.caju.transaction.adapter.dataprovider.WalletDataProvider
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.dto.MerchantDomain
import br.com.caju.transaction.domain.dto.WalletDomain
import br.com.caju.transaction.domain.usecase.wallet.WalletSubtractUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class WalletSubtractUseCaseTest {

    private val walletDataProvider: WalletDataProvider = mockk()
    private val usecase = WalletSubtractUseCase(walletDataProvider)

    @Test
    fun `should subtract wallet amount successfully when balance is sufficient`() {
        val account = AccountdDomain(number = "123")
        val transactionDomain = br.com.caju.transaction.domain.dto.TransactionDomain(
            amount = BigDecimal("50.00"),
            account = account,
            merchant = MerchantDomain(),
            type = TransactionType.MEAL
        )
        val walletDomain = WalletDomain(
            id = 1L,
            amount = BigDecimal("100.00"),
            type = TransactionType.MEAL,
            account = account
        )

        every { walletDataProvider.findByTransactionTypeAndAccountNumber(any(), any()) } returns walletDomain
        every { walletDataProvider.save(any()) } returns walletDomain.copy(amount = BigDecimal("50.00"))

        usecase.execute(transactionDomain, TransactionType.MEAL)

        verify {
            walletDataProvider.save(
                withArg { wallet ->
                    assertEquals(BigDecimal("50.00"), wallet.amount)
                }
            )
        }

        verify { walletDataProvider.findByTransactionTypeAndAccountNumber("123", TransactionType.MEAL) }
    }

    @Test
    fun `should not subtract wallet amount when balance is insufficient`() {
        val account = AccountdDomain(number = "123")
        val transactionDomain = br.com.caju.transaction.domain.dto.TransactionDomain(
            amount = BigDecimal("150.00"),
            account = account,
            merchant = MerchantDomain(),
            type = TransactionType.MEAL
        )
        val walletDomain = WalletDomain(
            id = 1L,
            amount = BigDecimal("100.00"),
            type = TransactionType.MEAL,
            account = account
        )

        every { walletDataProvider.findByTransactionTypeAndAccountNumber(any(), any()) } returns walletDomain
        usecase.execute(transactionDomain, TransactionType.MEAL)

        verify(exactly = 0) { walletDataProvider.save(any()) }

        verify { walletDataProvider.findByTransactionTypeAndAccountNumber("123", TransactionType.MEAL) }
    }
}