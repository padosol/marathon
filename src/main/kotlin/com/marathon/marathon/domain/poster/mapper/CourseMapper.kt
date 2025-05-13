package com.marathon.marathon.domain.poster.mapper

import com.marathon.marathon.domain.poster.dto.request.CourseDTO
import com.marathon.marathon.domain.poster.dto.response.CourseResponse
import com.marathon.marathon.domain.poster.entity.vo.Course

class CourseMapper {

    companion object {
        fun domainToResponse(course: Course): CourseResponse {
            return CourseResponse(
                distance =  course.distance,
                price =  course.price,
            )
        }

        fun dtoToDomain(courseDTO: CourseDTO): Course {
            return Course(
                distance =  courseDTO.distance,
                price = courseDTO.price
            )
        }
    }
}