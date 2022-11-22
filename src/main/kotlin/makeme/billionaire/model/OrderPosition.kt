package makeme.billionaire.model

enum class OrderPosition(val side: String) {
    LONG("BUY"),
    SHORT("SELL"),
}