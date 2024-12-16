package domain.usecase.transaction

import br.com.caju.transaction.adapter.dataprovider.TransactionDataProvider
import br.com.caju.transaction.core.entity.Wallet
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.dto.MerchantDomain
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.dto.WalletDomain
import br.com.caju.transaction.domain.exception.AccountNotFoundException
import br.com.caju.transaction.domain.factory.Factory
import br.com.caju.transaction.domain.strategy.TransactionFoodMccImpl
import br.com.caju.transaction.domain.strategy.TransactionStrategy
import br.com.caju.transaction.domain.usecase.account.GetAccountByAccountNumberUseCaso
import br.com.caju.transaction.domain.usecase.merchant.GetMerchantByNameOrMccUseCase
import br.com.caju.transaction.domain.usecase.transaction.CreateTransactionUseCase
import br.com.caju.transaction.domain.usecase.wallet.VerifyAmountUseCase
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateTransactionUseCaseTest {
    private val dataProvider: TransactionDataProvider = mockk<TransactionDataProvider>()
    private val accountUseCase: GetAccountByAccountNumberUseCaso = mockk<GetAccountByAccountNumberUseCaso>()
    private val getMerchantByNameOrMccUseCase: GetMerchantByNameOrMccUseCase = mockk<GetMerchantByNameOrMccUseCase>()
    private val verifyAmountUseCase: VerifyAmountUseCase = mockk<VerifyAmountUseCase>()
    private val transactionStrategy: TransactionStrategy = mockk<TransactionFoodMccImpl>()
    private val factory: Factory = mockk<Factory>()

    private val usecase = CreateTransactionUseCase(
        transactionDataProvider = dataProvider,
        accountNumberUseCaso = accountUseCase,
        merchantByNameOrMccUseCase = getMerchantByNameOrMccUseCase,
        verifyAmountUseCase = verifyAmountUseCase,
        factory = factory
    )

    @Test
    fun `should create transaction`() {

        val account = AccountdDomain(number = "123")

        val transactionDomain = TransactionDomain(
            account = account,
            amount = BigDecimal.ONE,
            type = TransactionType.MEAL,
            merchant = MerchantDomain(mcc = "1234")
        )
        every { dataProvider.save(any()) } returns transactionDomain
        every { accountUseCase.execute(any()) } returns account
        every { getMerchantByNameOrMccUseCase.execute(any()) } returns MerchantDomain(mcc = "102")
        every { transactionStrategy.process(any()) } returns WalletDomain(type = TransactionType.FOOD)
        every { verifyAmountUseCase.execute(any(), any()) } just  runs
        every { factory.getStrategyByMcc(any()) } returns transactionStrategy

        val response = usecase.execute(transactionDomain)

        assertNotNull(response.transactionCode)
        assertEquals("123", response.account!!.number)
        assertEquals("START", response.step)
    }

    @Test
    fun `should create transaction fail when account does not exists`() {

        val account = AccountdDomain(number = "123")

        val transactionDomain = TransactionDomain(
            account = account,
            amount = BigDecimal.ONE,
            type = TransactionType.MEAL,
            merchant = MerchantDomain(mcc = "1234")
        )
        every { dataProvider.save(any()) } returns transactionDomain
        every { accountUseCase.execute(any()) } returns null

        assertThrows<AccountNotFoundException> {  usecase.execute(transactionDomain) }
    }
}