package makeme.billionaire.model.dto

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
}