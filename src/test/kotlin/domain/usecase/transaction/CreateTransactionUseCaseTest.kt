package domain.usecase.transaction

import br.com.caju.transaction.adapter.dataprovider.TransactionDataProvider
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.dto.MerchantDomain
import br.com.caju.transaction.domain.dto.TransactionDomain
import br.com.caju.transaction.domain.exception.AccountNotFoundException
import br.com.caju.transaction.domain.usecase.account.GetAccountByAccountNumberUseCaso
import br.com.caju.transaction.domain.usecase.transaction.CreateTransactionUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CreateTransactionUseCaseTest {
    private val dataProvider: TransactionDataProvider = mockk<TransactionDataProvider>()
    private val accountUseCase: GetAccountByAccountNumberUseCaso = mockk<GetAccountByAccountNumberUseCaso>()

    private val usecase = CreateTransactionUseCase(
        transactionDataProvider = dataProvider,
        accountNumberUseCaso = accountUseCase
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