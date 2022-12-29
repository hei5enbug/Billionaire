package makeme.billionaire.model.dto

import org.springframework.format.annotation.DateTimeFormat
import org.ta4j.core.Trade.TradeType
import java.time.LocalDateTime

data class TradeResponse(
    val type: TradeType,
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val time: LocalDateTime,
    val price: Number,
    val amount: Number,
)
