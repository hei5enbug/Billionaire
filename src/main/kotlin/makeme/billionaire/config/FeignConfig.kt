package makeme.billionaire.config

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.ErrorDecoder
import makeme.billionaire.exception.FeignErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType


@Configuration
class FeignConfig {

    @Value("\${binance.key.api}")
    private lateinit var apiKey: String

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            requestTemplate.header("X-MBX-APIKEY", apiKey)
        }
    }

    @Bean
    fun errorDecode(): ErrorDecoder {
        return FeignErrorDecoder()
    }

}