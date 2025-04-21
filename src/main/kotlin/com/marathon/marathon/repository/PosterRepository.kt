package com.marathon.marathon.repository

import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.entity.Course
import com.marathon.marathon.entity.Poster
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PosterRepository(
    private val posterMongoRepository: PosterMongoRepository
) {

    fun save(poster: Poster): Poster {
        return posterMongoRepository.save(poster)
    }

    fun deletePoster(posterId: String) {
        posterMongoRepository.deleteById(posterId)
    }

    fun findById(posterId: String): Poster? {
        return posterMongoRepository.findByIdOrNull(posterId)
    }
    fun findAll(): List<Poster> {
        return posterMongoRepository.findAll()
    }

}