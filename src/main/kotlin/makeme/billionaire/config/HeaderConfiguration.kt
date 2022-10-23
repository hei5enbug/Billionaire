package makeme.billionaire.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType


class HeaderConfiguration {

    @Value("\${binance.key.api}")
    private lateinit var apiKey: String

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            requestTemplate.header("X-MBX-APIKEY", apiKey)
        }
    }

}