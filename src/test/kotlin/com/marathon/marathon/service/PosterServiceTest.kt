package com.marathon.marathon.service

import com.marathon.marathon.entity.Poster
import com.marathon.marathon.entity.vo.Course
import com.marathon.marathon.entity.vo.PosterStatus
import com.marathon.marathon.exception.CustomException
import com.marathon.marathon.repository.PosterRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertThrows
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

class PosterServiceTest : BehaviorSpec({

    val posterRepository = mockk<PosterRepository>()
    val posterService = PosterService(posterRepository)

    val poster = Poster(
        id = "poster1234",
        title = "2025 JTBC 마라톤 대회",
        location = "서울 올림픽 공원",
        startDate = LocalDateTime.of(2025, 7, 17, 12, 0),
        registrationStartDate = LocalDateTime.of(2025, 5, 17, 12, 0),
        registrationEndDate = LocalDateTime.of(2025, 5, 25, 12, 0),
        status = PosterStatus.UPCOMING,
        courses = mutableListOf(),
    )

    Given("posterId 가 주어졌을 때") {
        val posterId = "poster1234"

        every { posterRepository.findById(posterId) } returns poster
        When("posterId 로 요청하면 Poster 객체를 리턴한다.") {
            val findPoster = posterService.findPosterById(posterId);

            Then("posterId 와 찾은 posterId 가 동일하다.") {
                posterId shouldBe findPoster.id
            }
        }
    }

    Given("존재하지 않는 posterId 로 요청을 하면") {
        val posterId = "poster11111"
        every { posterRepository.findById(posterId) } returns null

        When("Custom Exception 이 발생한다.") {
            val exception = assertThrows<CustomException> {
                posterService.findPosterById(posterId)
            }
            Then("검증") {
                exception.statusCode shouldBe 404
                exception.errorMessage shouldBe "존재하지 않는 포스터 아이디 입니다. $posterId"
            }
        }
    }

})
