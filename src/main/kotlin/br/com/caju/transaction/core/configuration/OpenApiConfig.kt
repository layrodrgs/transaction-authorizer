package br.com.caju.transaction.core.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .info(
                        Info()
                                .title("Transaction API")
                                .version("1.0")
                                .description("Sistema para autorização de transações com regras de saldo e categorização.")
                )
    }
}
