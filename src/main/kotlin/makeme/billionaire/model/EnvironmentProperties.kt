package makeme.billionaire.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@Component
data class EnvironmentProperties(
    val binance: BinanceProperties
) {
    @ConstructorBinding
    @ConfigurationProperties(prefix = "binance")
    data class BinanceProperties(
        val url: String = "",
        val key: String = "",
        val secret: String = "",
    )
}

