package makeme.billionaire.exception

import makeme.billionaire.model.dto.BinanceResponseException
import makeme.billionaire.model.dto.ErrorResponse
import makeme.billionaire.util.Log
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object : Log;

    @ExceptionHandler(value = [NoSuchElementException::class])
    fun handleNoSuchElementFoundException(e: NoSuchElementException): ResponseEntity<ErrorResponse> {
        logger().error("", e)
        return ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message).toResponseEntity()
    }

    @ExceptionHandler(value = [ResponseStatusException::class])
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<ErrorResponse> {
        logger().error("", e)
        return ErrorResponse(e.statusCode.value(), e.message).toResponseEntity()
    }

    @ExceptionHandler(value = [BinanceResponseException::class])
    fun handleBinanceResponseException(e: BinanceResponseException): ResponseEntity<ErrorResponse> {
        logger().error("", e)
        return ErrorResponse(e.status, e.message).toResponseEntity()
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger().error("", e)
        return ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message).toResponseEntity()
    }

}
