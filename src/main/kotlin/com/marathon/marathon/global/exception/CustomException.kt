package com.marathon.marathon.global.exception

class CustomException(
    val statusCode: Int,
    val errorMessage: String
): RuntimeException() {
}