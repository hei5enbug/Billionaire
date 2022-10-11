package makeme.billionaire.model

data class SymbolsResponse(
    val symbols: List<SymbolResponse>
) {
    data class SymbolResponse(
        val symbol: String
    )
}