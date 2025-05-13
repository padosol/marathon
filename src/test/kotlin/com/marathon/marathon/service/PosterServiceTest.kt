package com.marathon.marathon.service

import com.marathon.marathon.domain.poster.dto.request.CourseDTO
import com.marathon.marathon.domain.poster.dto.request.CreatePosterDTO
import com.marathon.marathon.domain.poster.entity.Poster
import com.marathon.marathon.domain.poster.entity.vo.PosterStatus
import com.marathon.marathon.domain.poster.repository.PosterRepository
import com.marathon.marathon.domain.poster.service.PosterService
import com.marathon.marathon.global.exception.CustomException
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.swagger.v3.oas.annotations.media.Schema
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

class PosterServiceTest : BehaviorSpec({

    val posterRepository = mockk<PosterRepository>()
    val posterService = PosterService(posterRepository)

    val poster = Poster(
        id = "poster1",
        title = "2025 JTBC 마라톤 대회",
        location = "서울 올림픽 공원",
        startDate = LocalDateTime.of(2025, 7, 17, 12, 0),
        registrationStartDate = LocalDateTime.of(2025, 5, 17, 12, 0),
        registrationEndDate = LocalDateTime.of(2025, 5, 25, 12, 0),
        status = PosterStatus.UPCOMING,
        courses = mutableListOf(),
    )

    val poster2 = Poster(
        id = "poster2",
        title = "2025 동아 마라톤 대회",
        location = "서울 광화문 광장",
        startDate = LocalDateTime.of(2025, 3, 17, 12, 0),
        registrationStartDate = LocalDateTime.of(2025, 1, 17, 12, 0),
        registrationEndDate = LocalDateTime.of(2025, 1, 25, 12, 0),
        status = PosterStatus.CLOSED,
        courses = mutableListOf(),
    )

    val posters = listOf(
        poster, poster2

    )

    Given("유효한 포스터 ID 가 존재할 때") {
        val posterId = "poster1"

        every { posterRepository.findById(posterId) } returns poster
        When("해당 ID 로 포스터를 조회하면") {
            val findPoster = posterService.findPosterById(posterId);

            Then("조회한 포스터의 ID 는 요청한 ID 와 일치해야한다.") {
                posterId shouldBe findPoster.id
            }
        }
    }

    Given("존재하지 않는 포스터 ID 가 주어졌을 때") {
        val posterId = "poster11111"
        every { posterRepository.findById(posterId) } returns null

        When("해당 ID 로 포스터를 조회하면") {
            val exception = assertThrows<CustomException> {
                posterService.findPosterById(posterId)
            }
            Then("404 상태 코드와 에러 메시지를 포함한 CustomException 이 발생한다.") {
                exception.statusCode shouldBe 404
                exception.errorMessage shouldBe "존재하지 않는 포스터 아이디 입니다. $posterId"
            }
        }
    }

    Given("여러 포스터가 등록된 상태에서") {

        every { posterRepository.findAll() } returns posters

        When("전체 포스터 조회를 하면") {
            val findAllPoster = posterService.findAllPoster()

            Then("등록된 포스터가 정확히 반환된다.") {
                findAllPoster.size shouldBe posters.size

                findAllPoster shouldContainExactlyInAnyOrder posters
                // 모의 객체 호출 검증
                verify(exactly = 1) { posterRepository.findAll() }
            }
        }
    }


    Given("CreatePosterDTO 가 주어졌을 때 ") {
        val createPosterDTo = CreatePosterDTO(
            title = "새로운 마라톤 포스터",
            location = "대전광역시",
            startDate = LocalDateTime.now(),
            registrationStartDate = LocalDateTime.now(),
            registrationEndDate = LocalDateTime.now(),
            status = "UPCOMMING",
            courses =  mutableListOf()
        )





        When("포스터 등록 요청을 하면") {

            Then("포스터가 등록된다.")
        }
    }
})
