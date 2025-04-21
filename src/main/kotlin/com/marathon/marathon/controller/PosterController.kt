package com.marathon.marathon.controller

import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.dto.request.DeletePosterDTO
import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.dto.response.PosterResponse
import com.marathon.marathon.mapper.PosterMapper
import com.marathon.marathon.service.PosterService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
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
    private val posterService: PosterService,
) {

    @Operation(summary = "포스터 등록 API", description = "포스터를 등록합니다.")
    @PostMapping()
    fun createPoster(
        @RequestBody createPosterDTO: CreatePosterDTO
    ): ResponseEntity<PosterResponse> {
        val createPoster = posterService.createPoster(createPosterDTO)

        return ResponseEntity.ok(PosterMapper.entityToDTO(createPoster))
    }

    @Operation(summary = "포스터 수정 API", description = "포스터를 수정합니다.")
    @PutMapping("/{posterId}")
    fun modifyPoster(
        @PathVariable("posterId") posterId: String,
        @RequestBody modifyPosterDTO: ModifyPosterDTO
    ): ResponseEntity<PosterResponse> {
        val modifyPoster = posterService.modifyPoster(modifyPosterDTO)

        return ResponseEntity.ok(PosterMapper.entityToDTO(modifyPoster))
    }

    @Operation(summary = "포스터 삭제 API", description = "포스터를 삭제합니다.")
    @DeleteMapping("/{posterId}")
    fun deletePoster(
        @PathVariable("posterId") posterId: String,
        @RequestBody deletePosterDTO: DeletePosterDTO
    ): ResponseEntity<Void> {
        posterService.deletePoster(deletePosterDTO)

        return ResponseEntity.status(204).build()
    }
}