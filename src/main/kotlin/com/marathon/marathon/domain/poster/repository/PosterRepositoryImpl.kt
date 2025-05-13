package com.marathon.marathon.domain.poster.repository

import com.marathon.marathon.domain.poster.entity.Poster
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PosterRepositoryImpl(
    private val posterMongoRepository: PosterMongoRepository
) : PosterRepository {
    override fun save(poster: Poster): Poster {
        return posterMongoRepository.save(poster)
    }

    override fun deletePoster(posterId: String) {
        posterMongoRepository.deleteById(posterId)
    }

    override fun findById(posterId: String): Poster? {
        return posterMongoRepository.findByIdOrNull(posterId)
    }
    override fun findAll(): List<Poster> {
        return posterMongoRepository.findAll()
    }
}