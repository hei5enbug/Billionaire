package makeme.billionaire.exception

import makeme.billionaire.model.BinanceResponseException
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
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ResponseStatusException::class])
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(e.status.value(), e.message)
        return ResponseEntity(errorResponse, e.status)
    }

    @ExceptionHandler(value = [BinanceResponseException::class])
    fun handleBinanceResponseException(e: BinanceResponseException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(e.status, e.message)
        return ResponseEntity(errorResponse, HttpStatus.valueOf(e.status))
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}