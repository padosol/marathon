package com.marathon.marathon.global.exception

import java.time.LocalDateTime

data class ExceptionResponse(
    val statusCode: Int,
    val message: String,
    val timestamp: LocalDateTime
)
