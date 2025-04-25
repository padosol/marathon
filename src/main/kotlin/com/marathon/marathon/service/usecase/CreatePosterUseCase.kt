package com.marathon.marathon.service.usecase

import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.entity.Poster

interface CreatePosterUseCase {
    fun createPoster(createPosterDTO: CreatePosterDTO): Poster
}