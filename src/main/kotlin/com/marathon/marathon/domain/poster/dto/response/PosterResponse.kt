package com.marathon.marathon.domain.poster.dto.response

data class PosterResponse(
    val id: String,                    // 대회 포스터 아이디
    val title: String,                 // 대회명
    val location: String,              // 대회장소
    val startDate: String,                  // 대회 시작일
    val startTime: String,             // 대회 시작 시간
    val startDDay: Long,             // 대회 시작 D-Day

    val registrationStartDate: String, // 접수시작일
    val registrationStartDDay: Long, // 접수 시작 D-Day

    val registrationEndDate: String,   // 접수 마감일
    val registrationEndDDay: Long,   // 접수 마감 D-Day

    val status: String,          // 대회 상태

    val courses: List<CourseResponse>          // 대회 코스 정보
)