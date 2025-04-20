package com.marathon.marathon.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class CourseDTO(
    @field:Schema(description = "코스명", defaultValue = "풀")
    val courseName: String,
    @field:Schema(description = "코스 타입", defaultValue = "FULL")
    val courseType: String,
    @field:Schema(description = "가격", defaultValue = "50000")
    val price: Int
)