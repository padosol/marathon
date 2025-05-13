package com.marathon.marathon.domain.poster.repository

import com.marathon.marathon.domain.poster.entity.Poster
import org.springframework.data.mongodb.repository.MongoRepository

interface PosterMongoRepository: MongoRepository<Poster, String> {
}