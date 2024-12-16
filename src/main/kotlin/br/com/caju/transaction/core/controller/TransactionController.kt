package br.com.caju.transaction.core.controller

import br.com.caju.transaction.core.controller.dto.request.TransactionRequest
import br.com.caju.transaction.domain.usecase.transaction.CreateTransactionUseCase
import br.com.caju.transaction.adapter.converter.TransactionConverter
import br.com.caju.transaction.core.controller.dto.response.TransactionDefaultResponse
import br.com.caju.transaction.domain.usecase.transaction.SendTransactionQueueUseCase
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController (
    private val useCase: CreateTransactionUseCase,
    private val transactionQueueUseCase: SendTransactionQueueUseCase
){
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createTransaction(@RequestBody transactionRequest: TransactionRequest): TransactionDefaultResponse{
        useCase.execute(TransactionConverter.requestToDomain(transactionRequest))
            .also { transactionQueueUseCase.execute(it) }
        return TransactionDefaultResponse()
    }
}