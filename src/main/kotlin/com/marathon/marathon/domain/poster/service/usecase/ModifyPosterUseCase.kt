package com.marathon.marathon.domain.poster.service.usecase

import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.entity.Poster

interface ModifyPosterUseCase {
    fun modifyPoster(posterId: String, modifyPosterDTO: ModifyPosterDTO): Poster
}