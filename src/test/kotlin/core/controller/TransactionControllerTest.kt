package core.controller


import br.com.caju.transaction.core.controller.dto.request.TransactionRequest
import br.com.caju.transaction.domain.usecase.transaction.CreateTransactionUseCase
import br.com.caju.transaction.domain.usecase.transaction.SendTransactionQueueUseCase
import br.com.caju.transaction.adapter.converter.TransactionConverter
import br.com.caju.transaction.core.controller.TransactionController
import br.com.caju.transaction.core.controller.dto.response.TransactionDefaultResponse
import br.com.caju.transaction.core.entity.enumerated.TransactionType
import br.com.caju.transaction.core.handler.GlobalExceptionHandler
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

class TransactionControllerTest {

    private val createTransactionUseCase: CreateTransactionUseCase = mockk<CreateTransactionUseCase>()

    private val sendTransactionQueueUseCase: SendTransactionQueueUseCase = mockk<SendTransactionQueueUseCase>()

    private val controller = TransactionController(
        useCase = createTransactionUseCase,
        transactionQueueUseCase = sendTransactionQueueUseCase
    )

    private var mockMvc: MockMvc  = MockMvcBuilders
        .standaloneSetup(controller)
        .setControllerAdvice(GlobalExceptionHandler())
        .build()

    @Test
    fun `should create transaction and return success response`() {
        val transactionRequest = TransactionRequest(
            totalAmount = BigDecimal("150"),
            type = TransactionType.MEAL,
            mcc = "1234",
            merchant = "Merchant A",
            account = "Account123"
        )
        val transactionDomain = TransactionConverter.requestToDomain(transactionRequest)

        every { createTransactionUseCase.execute(any()) } returns (transactionDomain)
        every { sendTransactionQueueUseCase.execute(transactionDomain) } just runs

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsString(transactionRequest)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(TransactionDefaultResponse())))

        verify { createTransactionUseCase.execute(any()) }
        verify { createTransactionUseCase.execute(any()) }
    }

}