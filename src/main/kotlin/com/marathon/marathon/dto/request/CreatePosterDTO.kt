package com.marathon.marathon.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CreatePosterDTO(
    @field:Schema(description = "대회명", defaultValue = "2025 JTBC 마라톤대회")
    val title: String,
    
    @field:Schema(description = "대회장소", defaultValue = "서울")
    val location: String,

    @field:Schema(description = "대회 시작일", defaultValue = "20250715")
    val startDate: LocalDateTime,

    @field:Schema(description = "대회 접수일", defaultValue = "20250301")
    val registrationStartDate: LocalDateTime,

    @field:Schema(description = "대회 접수마감일", defaultValue = "20250315")
    val registrationEndDate: LocalDateTime,

    @field:Schema(description = "대회 상태", defaultValue = "UPCOMING")
    val status: String,

    @field:Schema(
        description = "마라톤 코스 정보",
    )
    val courses: List<CourseDTO> = mutableListOf()
)

