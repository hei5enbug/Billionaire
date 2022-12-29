package makeme.billionaire.model.dto

data class BackTestResponse(
    val count: Int,
    val winRate: Double,
    val profitLoss: Double,
    val tradeList: List<TradeResponse>
)
