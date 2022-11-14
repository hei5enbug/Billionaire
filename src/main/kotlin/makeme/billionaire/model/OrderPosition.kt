package makeme.billionaire.model

enum class OrderPosition {
    LONG {
        override fun toSide(): String {
            return "BUY"
        }
    },
    SHORT {
        override fun toSide(): String {
            return "SELL"
        }
    };

    abstract fun toSide(): String
}