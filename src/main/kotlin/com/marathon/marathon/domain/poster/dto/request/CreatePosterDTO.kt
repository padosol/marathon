package com.marathon.marathon.domain.poster.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CreatePosterDTO(
    @field:Schema(description = "대회명", defaultValue = "2025 JTBC 마라톤대회")
    val title: String,
    
    @field:Schema(description = "대회장소", defaultValue = "서울")
    val location: String,

    @field:Schema(description = "대회 시작일", defaultValue = "2025-07-15T12:00:00")
    val startDate: String,

    @field:Schema(description = "대회 접수일", defaultValue = "2025-03-01T00:00:00")
    val registrationStartDate: String,

    @field:Schema(description = "대회 접수마감일", defaultValue = "2025-03-15T00:00:00")
    val registrationEndDate: String,

    @field:Schema(
        description = "마라톤 코스 정보",
    )
    val courses: List<CourseDTO> = mutableListOf()
)

