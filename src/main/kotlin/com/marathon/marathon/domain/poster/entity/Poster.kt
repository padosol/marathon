package com.marathon.marathon.domain.poster.entity

import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.entity.vo.Course
import com.marathon.marathon.domain.poster.entity.vo.PosterStatus
import com.marathon.marathon.global.exception.CustomException
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document
data class Poster(
    @Id
    var id: String? = null,

    var title: String,
    val location: String,

    val startDate: LocalDateTime,
    val registrationStartDate: LocalDateTime,
    val registrationEndDate: LocalDateTime,

    var status: PosterStatus,

    @Field("courses")
    var courses: MutableList<Course> = mutableListOf(),
) {
    fun modifyPoster(modifyPosterDTO: ModifyPosterDTO) {
        if (id == null) {
            throw IllegalArgumentException("ID 가 존재하지 않는 포스터는 수정할 수 없습니다.")
        }

        if (!id.equals(modifyPosterDTO.id)) {
            throw IllegalArgumentException(
                "동일한 ID 가 아니면 수정할 수 없습니다. 기존 ID: $id 대상 ID: ${modifyPosterDTO.id}"
            )
        }

        this.title = modifyPosterDTO.posterName
        this.courses = mutableListOf()
    }
}


