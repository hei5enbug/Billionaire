package makeme.billionaire.model

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "binance")
data class EnvironmentProperties(
    val api: ApiProperties,
    val url: UrlProperties,
) {
    data class UrlProperties(
        val candleInfo: String,
        val symbolList: String,
    )

    data class ApiProperties(
        val key: String,
        val secret: String,
    )
}

