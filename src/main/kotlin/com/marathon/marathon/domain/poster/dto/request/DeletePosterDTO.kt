package com.marathon.marathon.domain.poster.dto.request

import io.swagger.v3.oas.annotations.media.Schema

data class DeletePosterDTO(
    @field:Schema(description = "포스터 아이디")
    val posterId: String
)
