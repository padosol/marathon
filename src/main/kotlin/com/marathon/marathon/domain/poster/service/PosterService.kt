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
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
@Service
class PosterService(
    private val posterRepository: PosterRepository
) : GetPosterUseCase, CreatePosterUseCase, ModifyPosterUseCase, RemovePosterUseCase {

    private val log = LoggerFactory.getLogger(PosterService::class.java)

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

        val savePoster = posterRepository.save(poster)

        log.info("포스터 등록 완료: {}", savePoster)

        return savePoster
    }

    override fun modifyPoster(posterId: String, modifyPosterDTO: ModifyPosterDTO): Poster {
        val findPoster: Poster = posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            )

        findPoster.modifyPoster(modifyPosterDTO)

        val savePoster = posterRepository.save(findPoster)

        log.info("포스터 수정 완료: {}", savePoster)

        return savePoster
    }

    override fun removePoster(posterId: String) {
        val poster = (posterRepository.findById(posterId)
            ?: throw CustomException(
                statusCode = HttpStatus.NOT_FOUND.value(),
                errorMessage = "존재하지 않는 포스터 아이디 입니다. $posterId"
            ))

        posterRepository.deletePoster(posterId)

        log.info("포스터 삭제 완료: {} ", poster)
    }
}