package br.com.caju.transaction.domain.exception

import java.lang.RuntimeException

class InsuficientAmountException(
    message: String?

) : RuntimeException(message) {
}