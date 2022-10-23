package makeme.billionaire.model.dto

data class LeverageResponse(
    val symbol: String,
    val leverage: Int,
    val maxNotionalValue: String
)
