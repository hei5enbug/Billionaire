package makeme.billionaire.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "binance.key")
data class BinanceProperties(
    val api: String,
    val secret: String,
)