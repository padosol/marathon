package com.marathon.marathon.global.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime


@RestControllerAdvice
class CustomExceptionHandler {
    private val log = LoggerFactory.getLogger(CustomExceptionHandler::class.java)

    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(customException: CustomException): ResponseEntity<ExceptionResponse> {
        log.info(customException.message)

        return ResponseEntity.status(customException.statusCode).body(
            ExceptionResponse(
                statusCode = customException.statusCode,
                message = customException.errorMessage,
                timestamp = LocalDateTime.now()
            )
        )
    }

}