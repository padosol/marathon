package com.marathon.marathon.mapper

import com.marathon.marathon.dto.request.CourseDTO
import com.marathon.marathon.dto.response.CourseResponse
import com.marathon.marathon.entity.vo.Course

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