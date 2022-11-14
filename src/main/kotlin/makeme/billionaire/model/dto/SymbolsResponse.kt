package makeme.billionaire.model.dto

data class SymbolsResponse(
    val symbols: List<SymbolResponse>
) {
    data class SymbolResponse(
        val symbol: String
    )
}