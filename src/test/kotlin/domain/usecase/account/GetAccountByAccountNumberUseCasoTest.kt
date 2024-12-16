package domain.usecase.account

import br.com.caju.transaction.adapter.dataprovider.AccountDataProvider
import br.com.caju.transaction.domain.dto.AccountdDomain
import br.com.caju.transaction.domain.usecase.account.GetAccountByAccountNumberUseCaso
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetAccountByAccountNumberUseCasoTest {
    private val dataProvider: AccountDataProvider = mockk<AccountDataProvider>()

    private val usecase = GetAccountByAccountNumberUseCaso(
        accountDataProvider = dataProvider
    )

    @Test
    fun `should get account by number`() {

        every { dataProvider.findByAccountNumber(any()) } returns (AccountdDomain(number = "123"))

        val response = usecase.execute("123")

        assertEquals("123", response!!.number)
    }

}