package com.marathon.marathon.service

import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.entity.vo.Course
import com.marathon.marathon.entity.Poster
import com.marathon.marathon.entity.vo.PosterStatus
import com.marathon.marathon.exception.CustomException
import com.marathon.marathon.mapper.CourseMapper
import com.marathon.marathon.repository.PosterRepository
import com.marathon.marathon.service.usecase.CreatePosterUseCase
import com.marathon.marathon.service.usecase.GetPosterUseCase
import com.marathon.marathon.service.usecase.ModifyPosterUseCase
import com.marathon.marathon.service.usecase.RemovePosterUseCase
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PosterService(
    private val posterRepository: PosterRepository
) : GetPosterUseCase, CreatePosterUseCase, ModifyPosterUseCase, RemovePosterUseCase{

    override fun findPosterById(posterId: String):Poster {
        return posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )
    }

    override fun findAllPoster():List<Poster> {
        return posterRepository.findAll()
    }

    override fun createPoster(createPosterDTO: CreatePosterDTO): Poster {
        val poster = Poster(
            title =  createPosterDTO.title,
            location =  createPosterDTO.location,
            startDate =  createPosterDTO.startDate,
            registrationStartDate =  createPosterDTO.registrationStartDate,
            registrationEndDate =  createPosterDTO.registrationEndDate,
            status = PosterStatus.valueOf(createPosterDTO.status),
            courses = createPosterDTO.courses.map { CourseMapper.dtoToDomain(it) }.toMutableList(),
        )

        return posterRepository.save(poster)
    }

    override fun modifyPoster(posterId: String, modifyPosterDTO: ModifyPosterDTO): Poster {
        val findPoster: Poster = posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )

        findPoster.modifyPoster(modifyPosterDTO)

        return posterRepository.save(findPoster)
    }

    override fun removePoster(posterId: String) {
        posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )

        posterRepository.deletePoster(posterId)
    }
}