package com.marathon.marathon.domain.poster.service.usecase

import com.marathon.marathon.domain.poster.dto.request.CreatePosterDTO
import com.marathon.marathon.domain.poster.entity.Poster

interface CreatePosterUseCase {
    fun createPoster(createPosterDTO: CreatePosterDTO): Poster
}