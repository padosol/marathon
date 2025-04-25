package com.marathon.marathon.service.usecase

import com.marathon.marathon.entity.Poster

interface GetPosterUseCase {
    fun findPosterById(posterId: String): Poster

    fun findAllPoster():List<Poster>
}