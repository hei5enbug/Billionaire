package makeme.billionaire.model.dto

import org.ta4j.core.BaseBar
import org.ta4j.core.num.DecimalNum
import java.time.*

data class CandleResponse(
    val openTime: Long,
    val openPrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val closePrice: Double,
    val volume: Double,
    val closeTime: Long,
) {
    constructor(response: List<String>) : this(
        response[0].toLong(),
        response[1].toDouble(),
        response[2].toDouble(),
        response[3].toDouble(),
        response[4].toDouble(),
        response[5].toDouble(),
        response[6].toLong(),
    )

    fun toBaseBar(): BaseBar {
        return BaseBar.builder(DecimalNum::valueOf, Number::class.java)
            .timePeriod(
                Duration.between(
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(openTime), ZoneId.systemDefault()),
                    LocalDateTime.ofInstant(Instant.ofEpochMilli(closeTime), ZoneId.systemDefault()),
                ),
            )
            .endTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(closeTime), ZoneId.systemDefault()))
            .openPrice(openPrice)
            .highPrice(highPrice)
            .lowPrice(lowPrice)
            .closePrice(closePrice)
            .volume(volume)
            .build()
    }
}
