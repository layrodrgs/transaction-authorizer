package br.com.caju.transaction.domain.exception

import java.lang.RuntimeException

class MerchantNotFoundException(
    message: String?

) : RuntimeException(message) {
}