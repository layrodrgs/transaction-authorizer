package br.com.caju.transaction.core.handler

import br.com.caju.transaction.core.controller.dto.response.TransactionDefaultResponse
import io.swagger.v3.oas.annotations.media.Schema
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler (
){
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<TransactionDefaultResponse> {
        log.error("error, ", ex)
        val errorResponse = TransactionDefaultResponse(code = "07")
        return ResponseEntity(errorResponse, HttpStatus.OK)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<TransactionDefaultResponse> {
        log.error("error, ", ex)
        val errorResponse = TransactionDefaultResponse(code = "07")
        return ResponseEntity(errorResponse, HttpStatus.OK)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<TransactionDefaultResponse> {
        log.error("error, ", ex)
        val errorResponse = TransactionDefaultResponse(code = "07")
        return ResponseEntity(errorResponse, HttpStatus.OK)
    }
}