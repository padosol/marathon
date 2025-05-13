package com.marathon.marathon.domain.poster.service.usecase

import com.marathon.marathon.domain.poster.entity.Poster

interface GetPosterUseCase {
    fun findPosterById(posterId: String): Poster

    fun findAllPoster():List<Poster>
}