package makeme.billionaire.model

enum class TimeInterval(
    val parameter: String,
    val millis: Long,
) {
    Minute1("1m", 1 * 60 * 1000),
    Minutes5("5m", 5 * 60 * 1000),
    Minutes15("15m", 15 * 60 * 1000),
    Minutes30("30m", 30 * 60 * 1000),
    Hour1("1h", 1 * 60 * 60 * 1000),
    Hours4("4h", 4 * 60 * 60 * 1000),
    Day1("1d", 1 * 24 * 60 * 60 * 1000),
}
