package makeme.billionaire.model


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class BinanceProperties(
    @Value("\${binance.api}")
    val apiKey: String,

    @Value("\${binance.secret}")
    val secretKey: String
)
