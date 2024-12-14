package br.com.caju.transaction.domain.exception

import java.lang.RuntimeException

class AccountNotFoundException(
    message: String?

) : RuntimeException(message) {
}