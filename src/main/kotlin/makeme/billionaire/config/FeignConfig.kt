package makeme.billionaire.config

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.ErrorDecoder
import makeme.billionaire.exception.FeignErrorDecoder
import makeme.billionaire.model.BinanceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType


@Configuration
class FeignConfig(
    val binanceProperties: BinanceProperties
) {

    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            requestTemplate.header("X-MBX-APIKEY", binanceProperties.apiKey)
        }
    }

    @Bean
    fun errorDecode(): ErrorDecoder {
        return FeignErrorDecoder()
    }

}
