package com.marathon.marathon.entity

import com.marathon.marathon.dto.request.ModifyPosterDTO
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Poster(
    @Id
    var id: String? = null,

    var posterName: String,

    @Field("courses")
    var courses: MutableList<Course> = mutableListOf(),
) {
    fun modifyPoster(modifyPosterDTO: ModifyPosterDTO) {
        this.posterName = modifyPosterDTO.posterName
        this.courses = modifyPosterDTO.courses.map { Course(
            courseName = it.courseName,
            courseType = it.courseType,
            prise = it.price
        ) }.toMutableList()
    }
}


