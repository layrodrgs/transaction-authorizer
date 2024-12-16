package br.com.caju.transaction.core.configuration

import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private const val EXCHANGE_NAME = "transaction.exchange"
private const val DEAD_LETTER_EXCHANGE = "transaction.dql.exchange"
private const val QUEUE_NAME = "transaction.queue"
private const val DEAD_LETTER_QUEUE_NAME = "transaction.dql"
private const val ROUTING_KEY = "transaction.routing.key"
private const val DEAD_LETTER_ROUTING_KEY = "transaction.dlq.routing.key"
@Configuration
class RabbitConfig {
    @Bean
    fun exchange(): DirectExchange {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).build()
    }

    @Bean
    fun deadLetterExchange(): DirectExchange {
        return ExchangeBuilder.directExchange(DEAD_LETTER_EXCHANGE).build()
    }

    @Bean
    fun transactionQueue(): Queue {
        return QueueBuilder.durable(QUEUE_NAME)
            .deadLetterExchange(DEAD_LETTER_EXCHANGE)
            .deadLetterRoutingKey(DEAD_LETTER_ROUTING_KEY)
            .build()
    }

    @Bean
    fun deadLetterQueue(): Queue {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_NAME).build()
    }

    @Bean
    fun deadLetterBinding(): Binding {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY)
    }

    @Bean
    fun binding(): Binding {
        return BindingBuilder.bind(transactionQueue()).to(exchange()).with(ROUTING_KEY)
    }

    @Bean
    fun converter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}