package makeme.billionaire.model


data class BinanceResponseException(
    var status: Int = 500,
    val code: String,
    val msg: String,
) : RuntimeException(msg)