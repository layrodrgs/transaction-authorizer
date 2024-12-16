package br.com.caju.transaction.domain.exception

import java.lang.RuntimeException

class WalletNotFound(
    message: String?

) : RuntimeException(message) {
}