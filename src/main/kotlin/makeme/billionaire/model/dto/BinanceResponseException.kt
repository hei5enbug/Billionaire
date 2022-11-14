package makeme.billionaire.model.dto


data class BinanceResponseException(
    var status: Int = 500,
    val code: String,
    val msg: String,
) : RuntimeException(msg)