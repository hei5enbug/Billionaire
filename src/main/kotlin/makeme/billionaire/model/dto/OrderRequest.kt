package makeme.billionaire.model.dto

data class OrderRequest(
    val symbol: String,
    val leverage: Int = 1,
    val position: String,
    val ratio: Double = 0.0,
    val id: String,
    val password: String,
    val side: String = when (position.uppercase()) {
        "LONG" -> "BUY"
        "SHORT" -> "SELL"
        else -> ""
    }
)
