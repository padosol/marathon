package com.marathon.marathon.global.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception
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

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(exception: Exception): ResponseEntity<ExceptionResponse> {
        log.info(exception.message)

        return ResponseEntity.status(500).body(
            ExceptionResponse(
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "서버에러 입니다.",
                timestamp = LocalDateTime.now()
            )
        )
    }

}