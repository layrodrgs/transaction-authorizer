package br.com.caju.transaction.core.controller

import br.com.caju.transaction.core.controller.dto.request.TransactionRequest
import br.com.caju.transaction.domain.usecase.transaction.CreateTransactionUseCase
import br.com.caju.transaction.adapter.converter.TransactionConverter
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController (
    private val useCase: CreateTransactionUseCase
){
    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun createTransaction(transactionRequest: TransactionRequest){
        useCase.execute(TransactionConverter.requestToDomain(transactionRequest))
    }

    @GetMapping
    fun getTransactions(){

    }
}