package com.marathon.marathon.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class CreatePosterDTO(
    @field:Schema(description = "포스터 이름", defaultValue = "마라톤 포스터")
    val posterName: String,
    @field:Schema(
        description = "마라톤 코스 정보",
    )
    val courses: List<CourseDTO> = mutableListOf()
)

