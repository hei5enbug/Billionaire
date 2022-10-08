package makeme.billionaire.model

data class ErrorResponse(
    var status: Int? = null,
    var message: String? = null
)