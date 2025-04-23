package com.marathon.marathon.controller

import com.marathon.marathon.entity.Poster
import com.marathon.marathon.service.PosterService
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.test.web.servlet.MockMvc

class PosterControllerTest : DescribeSpec({

    lateinit var mockMvc: MockMvc
    val posterService = mockk<PosterService>()


    describe("GET /api/posters/{posterId} 요청이 들어왔을 때") {
        val posterId: String = "posterId"
        val url = "/api/posters/$posterId"
        
        context("포스터가 존재하면") {

            val poster: Poster = Poster(
                id = posterId,
                posterName = "포스터",
                courses = mutableListOf()
            )

            every { posterService.findPosterById(posterId) }

            it("200 상태코드와 포스터 정보를 반환한다.") {

            }
        }
    }

})