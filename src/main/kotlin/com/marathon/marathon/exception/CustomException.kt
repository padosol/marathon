package com.marathon.marathon.exception

class CustomException(
    val statusCode: Int,
    val errorMessage: String
): RuntimeException() {
}