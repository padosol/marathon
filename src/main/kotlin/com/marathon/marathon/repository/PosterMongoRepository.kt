package com.marathon.marathon.repository

import com.marathon.marathon.entity.Poster
import org.springframework.data.mongodb.repository.MongoRepository

interface PosterMongoRepository: MongoRepository<Poster, String> {
}