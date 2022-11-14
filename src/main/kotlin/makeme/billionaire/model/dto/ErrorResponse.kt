package makeme.billionaire.model.dto

data class ErrorResponse(
    var status: Int? = null,
    var message: String? = null
)