package makeme.billionaire.model.dto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class ErrorResponse(
    var status: Int,
    var message: String?,
) {
    fun toResponseEntity(): ResponseEntity<ErrorResponse> {
        return ResponseEntity(this, HttpStatus.valueOf(status))
    }

}
