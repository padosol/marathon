package com.marathon.marathon.domain.poster.service

import com.marathon.marathon.domain.poster.dto.request.CreatePosterDTO
import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.entity.Poster
import com.marathon.marathon.domain.poster.entity.vo.PosterStatus
import com.marathon.marathon.domain.poster.mapper.CourseMapper
import com.marathon.marathon.domain.poster.mapper.PosterMapper
import com.marathon.marathon.domain.poster.repository.PosterRepository
import com.marathon.marathon.domain.poster.service.usecase.CreatePosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.GetPosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.ModifyPosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.RemovePosterUseCase
import com.marathon.marathon.global.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
@Service
class PosterService(
    private val posterRepository: PosterRepository
) : GetPosterUseCase, CreatePosterUseCase, ModifyPosterUseCase, RemovePosterUseCase {

    override fun findPosterById(posterId: String): Poster {
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
        val poster = PosterMapper.createDtoToEntity(createPosterDTO)

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