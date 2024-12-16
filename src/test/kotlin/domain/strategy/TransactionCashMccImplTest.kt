package domain.strategy

import br.com.caju.transaction.domain.dto.WalletDomain
import br.com.caju.transaction.domain.strategy.TransactionCashMccImpl
import br.com.caju.transaction.domain.usecase.wallet.FindByTransactionTypeAndAccountNumberUseCase
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TransactionCashMccImplTest {
    private val findByTransactionTypeAndAccountNumberUseCase: FindByTransactionTypeAndAccountNumberUseCase = mockk<FindByTransactionTypeAndAccountNumberUseCase>()


    private val transacton = TransactionCashMccImpl(
        findByTransactionTypeAndAccountNumberUseCase = findByTransactionTypeAndAccountNumberUseCase
    )

    @Test
    fun `should veriify mcc for cash`() {
        every { findByTransactionTypeAndAccountNumberUseCase.execute(any(), any()) } returns WalletDomain()

        assertTrue { transacton.equalsMcc("123") }
    }
}