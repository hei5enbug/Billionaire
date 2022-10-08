package makeme.billionaire.exception

import makeme.billionaire.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNoSuchElementFoundException(e: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ResponseStatusException::class])
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            e.status.value(),
            e.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

}