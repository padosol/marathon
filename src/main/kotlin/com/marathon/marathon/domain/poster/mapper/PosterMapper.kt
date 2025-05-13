package com.marathon.marathon.domain.poster.mapper

import com.marathon.marathon.domain.poster.dto.response.PosterResponse
import com.marathon.marathon.domain.poster.entity.Poster
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class PosterMapper {
    companion object {

        fun entityToDTO(poster: Poster): PosterResponse {
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val now = LocalDateTime.now()

            return PosterResponse(
                id =  poster.id!!,
                title = poster.title,
                location = poster.location,
                startDate = dateFormatter.format(poster.startDate),
                startTime = timeFormatter.format(poster.startDate),
                startDDay = ChronoUnit.DAYS.between(now, poster.startDate),
                registrationStartDate = dateFormatter.format(poster.registrationStartDate),
                registrationStartDDay = ChronoUnit.DAYS.between(now, poster.registrationStartDate),
                registrationEndDate = dateFormatter.format(poster.registrationEndDate),
                registrationEndDDay = ChronoUnit.DAYS.between(now, poster.registrationEndDate),
                status = poster.status.name,
                courses = poster.courses.map { CourseMapper.domainToResponse(it) }
            )
        }
    }
}