package com.marathon.marathon.domain.poster.entity

import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.entity.vo.Course
import com.marathon.marathon.domain.poster.entity.vo.PosterStatus
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
        this.title = modifyPosterDTO.posterName
        this.courses = mutableListOf()
    }
}


