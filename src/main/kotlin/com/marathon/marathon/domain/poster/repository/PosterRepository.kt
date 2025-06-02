package com.marathon.marathon.domain.poster.repository

import com.marathon.marathon.domain.poster.entity.Poster


interface PosterRepository {
    fun save(poster: Poster): Poster
    fun deletePoster(posterId: String)
    fun findById(posterId: String): Poster?
    fun findAll(): List<Poster>

    fun deleteAll()
}