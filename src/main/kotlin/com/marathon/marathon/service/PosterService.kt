package com.marathon.marathon.service

import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.entity.Course
import com.marathon.marathon.entity.Poster
import com.marathon.marathon.exception.CustomException
import com.marathon.marathon.repository.PosterRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class PosterService(
    private val posterRepository: PosterRepository
) {

    fun findPosterById(posterId: String):Poster {
        return posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )
    }

    fun findAllPoster():List<Poster> {
        return posterRepository.findAll()
    }

    fun createPoster(createPosterDTO: CreatePosterDTO): Poster {
        val poster = Poster(
            posterName = createPosterDTO.posterName,
            courses = createPosterDTO.courses.map {
                Course(
                    courseName = it.courseName,
                    courseType = it.courseType,
                    prise = it.price
                )
            }.toMutableList(),
            posterId = posterId
        )

        return posterRepository.save(poster)
    }

    fun modifyPoster(posterId: String, modifyPosterDTO: ModifyPosterDTO): Poster {
        val findPoster: Poster = posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )

        findPoster.modifyPoster(modifyPosterDTO)

        return posterRepository.save(findPoster)
    }

    fun deletePoster(posterId: String) {
        posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )

        posterRepository.deletePoster(posterId)
    }
}