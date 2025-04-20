package com.marathon.marathon.mapper

import com.marathon.marathon.dto.response.PosterResponse
import com.marathon.marathon.entity.Poster

class PosterMapper {
    companion object {

        fun entityToDTO(poster: Poster): PosterResponse {
            return PosterResponse(
                id =  poster.id!!,
                posterName =  poster.posterName,
            )
        }

    }
}