package makeme.billionaire.model.dto

data class MarketPriceResponse(
    val symbol: String,
    val markPrice: Double,
    val indexPrice: Double,
    val estimatedSettlePrice: Double,
    val lastFundingRate: Double,
    val interestRate: Double,
    val nextFundingTime: Long,
    val time: Long,
)
