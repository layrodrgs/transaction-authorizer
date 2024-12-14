package domain.strategy

import br.com.caju.transaction.domain.strategy.TransactionCashMccImpl
import br.com.caju.transaction.domain.usecase.wallet.WalletSubtractUseCase
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TransactionCashMccImplTest {
    private val walletSubtractUseCase: WalletSubtractUseCase = mockk<WalletSubtractUseCase>()

    private val transacton = TransactionCashMccImpl(
        walletSubtractUseCase = walletSubtractUseCase
    )

    @Test
    fun `should veriify mcc for cash`() {

        every { walletSubtractUseCase.execute(any(), any()) } just runs

        assertTrue { transacton.equalsMcc("123") }
    }
}