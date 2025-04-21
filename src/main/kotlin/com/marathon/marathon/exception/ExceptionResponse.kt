package com.marathon.marathon.exception

import java.time.LocalDateTime

data class ExceptionResponse(
    val statusCode: Int,
    val message: String,
    val timestamp: LocalDateTime
)
