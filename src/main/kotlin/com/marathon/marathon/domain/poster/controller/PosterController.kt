package com.marathon.marathon.domain.poster.controller

import com.marathon.marathon.domain.poster.dto.request.CreatePosterDTO
import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.dto.response.PosterResponse
import com.marathon.marathon.domain.poster.mapper.PosterMapper
import com.marathon.marathon.domain.poster.service.usecase.CreatePosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.GetPosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.ModifyPosterUseCase
import com.marathon.marathon.domain.poster.service.usecase.RemovePosterUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Poster API")
@RestController
@RequestMapping("/api/posters")
class PosterController(
    private val getPosterUseCase: GetPosterUseCase,
    private val createPosterUseCase: CreatePosterUseCase,
    private val modifyPosterUseCase: ModifyPosterUseCase,
    private val removePosterUseCase: RemovePosterUseCase
) {

    @Operation(summary = "포스터 조회 API", description = "posterId 로 포스터를 조회한다.")
    @GetMapping("/{posterId}")
    fun findPoster(
        @PathVariable("posterId") posterId: String
    ): ResponseEntity<PosterResponse> {
        val poster = getPosterUseCase.findPosterById(posterId)
        return ResponseEntity.ok(PosterMapper.entityToDTO(poster))
    }

    @Operation(summary = "포스터 전체 조회 API", description = "전체 포스터를 조회한다.")
    @GetMapping()
    fun findAllPoster(): ResponseEntity<List<PosterResponse>> {
        return ResponseEntity.ok(
            getPosterUseCase.findAllPoster().map { PosterMapper.entityToDTO(it) }
        )
    }

    @Operation(summary = "포스터 등록 API", description = "포스터를 등록합니다.")
    @PostMapping()
    fun createPoster(
        @RequestBody createPosterDTO: CreatePosterDTO
    ): ResponseEntity<PosterResponse> {
        val createPoster = createPosterUseCase.createPoster(createPosterDTO)

        return ResponseEntity.ok(PosterMapper.entityToDTO(createPoster))
    }

    @Operation(summary = "포스터 수정 API", description = "포스터를 수정합니다.")
    @PutMapping("/{posterId}")
    fun modifyPoster(
        @PathVariable("posterId") posterId: String,
        @RequestBody modifyPosterDTO: ModifyPosterDTO
    ): ResponseEntity<PosterResponse> {
        val modifyPoster = modifyPosterUseCase.modifyPoster(posterId, modifyPosterDTO)

        return ResponseEntity.ok(PosterMapper.entityToDTO(modifyPoster))
    }

    @Operation(summary = "포스터 삭제 API", description = "포스터를 삭제합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "포스터 삭제 성공",
                content = [
                    Content(
                        mediaType = "application/json"
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "존재하지 않는 포스터 입니다.",
                content = [
                    Content(
                        mediaType = "application/json"
                    )
                ]
            )
        ]
    )
    @DeleteMapping("/{posterId}")
    fun deletePoster(
        @PathVariable("posterId") posterId: String,
    ): ResponseEntity<Void> {
        removePosterUseCase.removePoster(posterId)

        return ResponseEntity.status(204).build()
    }

}