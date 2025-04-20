package com.marathon.marathon.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class ModifyPosterDTO(
    @field:Schema(description = "포스터 아이디")
    val id: String,
    @field:Schema(description = "포스터 이름", defaultValue = "포스터명 변경")
    val posterName: String,
    val courses: List<CourseDTO> = mutableListOf()
)
