package com.marathon.marathon.repository

import com.marathon.marathon.entity.Poster

interface PosterRepository {
    fun save(poster: Poster): Poster
    fun deletePoster(posterId: String)
    fun findById(posterId: String): Poster?
    fun findAll(): List<Poster>
}