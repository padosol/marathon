package com.marathon.marathon.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class CourseDTO(
    @field:Schema(description = "코스거리", defaultValue = "10")
    val distance: String,
    @field:Schema(description = "가격", defaultValue = "50000")
    val price: Int
)