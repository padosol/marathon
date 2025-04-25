package com.marathon.marathon.service.usecase

import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.entity.Poster

interface ModifyPosterUseCase {
    fun modifyPoster(posterId: String, modifyPosterDTO: ModifyPosterDTO): Poster
}