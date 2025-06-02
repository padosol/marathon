package com.marathon.marathon.domain.poster.service

import com.marathon.marathon.domain.poster.dto.request.CourseDTO
import com.marathon.marathon.domain.poster.dto.request.CreatePosterDTO
import com.marathon.marathon.domain.poster.dto.request.ModifyPosterDTO
import com.marathon.marathon.domain.poster.entity.Poster
import com.marathon.marathon.domain.poster.entity.vo.Course
import com.marathon.marathon.domain.poster.repository.PosterRepository
import com.marathon.marathon.global.exception.CustomException
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.swagger.v3.oas.annotations.media.Schema
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class PosterServiceTest(
    private val posterRepository: PosterRepository,
    private val posterService: PosterService
) : BehaviorSpec({

    beforeEach {
        posterRepository.deleteAll()
    }

    Given("포스터가 등록되어 있을 때") {
        val course1 = Course(distance = "42", price = 50000)
        val course2 = Course(distance = "21", price = 35000)

        val poster = Poster(
            title = "2025 marathon",
            location = "서울",
            startDate = LocalDateTime.of(2025, 5, 17, 10, 0),
            registrationStartDate = LocalDateTime.of(2025, 3, 17, 10, 0),
            registrationEndDate = LocalDateTime.of(2025, 3, 25, 10, 0),
            courses = mutableListOf(course1, course2)
        )
        val savedPoster = posterRepository.save(poster)
        val posterId = savedPoster.id!!

        When("해당 포스터 아이디로 조회를 하면") {
            val findPoster = posterService.findPosterById(posterId)

            Then("포스터를 조회할 수 있다.") {
                findPoster.id shouldBe posterId
            }
        }

    }

    Given("createPosterDTO 가 주어졌을 때") {

        val courseDTO1 = CourseDTO("42", 50000)
        val courseDTO2 = CourseDTO("21", 35000)

        val courses: List<CourseDTO> = mutableListOf(courseDTO1, courseDTO2)
        val createPosterDTO = CreatePosterDTO(
            title = "2025 JTBC 마라톤 대회",
            location = "서울",
            startDate = "2025-07-15T12:00:00",
            registrationStartDate = "2025-03-01T00:00:00",
            registrationEndDate = "2025-03-15T00:00:00",
            courses = courses
        )

        When("등록을 하면") {
            val createPoster = posterService.createPoster(createPosterDTO)

            Then("정상적으로 등록이 된다.") {
                createPoster.title shouldBe createPosterDTO.title

            }
        }
    }

    Given("등록된 포스터가 존재 할 때") {
        val course1 = Course(distance = "42", price = 50000)
        val course2 = Course(distance = "21", price = 35000)

        val poster = Poster(
            title = "2025 marathon",
            location = "서울",
            startDate = LocalDateTime.of(2025, 5, 17, 10, 0),
            registrationStartDate = LocalDateTime.of(2025, 3, 17, 10, 0),
            registrationEndDate = LocalDateTime.of(2025, 3, 25, 10, 0),
            courses = mutableListOf(course1, course2)
        )
        val savedPoster = posterRepository.save(poster)
        val posterId = savedPoster.id!!

        When("해당 포스터 아이디로 삭제를 하면") {
            posterService.removePoster(posterId)

            Then("삭제되어 해당 아이디로 조회가 되지 않는다.") {
                val exception = assertThrows<CustomException> {
                    posterService.findPosterById(posterId)
                }

                exception.statusCode shouldBe 404
                exception.errorMessage shouldBe "존재하지 않는 포스터 아이디 입니다. $posterId"
            }
        }
    }

    Given("포스터 삭제 에러 테스트") {
        val posterId = "posterId1"

        When("존재하지 않는 포스터 아이디로 삭제를 하면") {
            val exception = assertThrows<CustomException> {
                posterService.removePoster(posterId)
            }

            Then("404 상태코드와 함께 에러 메시지를 리턴한다.") {
                exception.statusCode shouldBe 404
                exception.errorMessage shouldBe "존재하지 않는 포스터 아이디 입니다. $posterId"
            }
        }
    }

    Given("2개의 포스터가 등록되어 있을 때") {
        val course1 = Course(distance = "42", price = 50000)
        val course2 = Course(distance = "21", price = 35000)

        val poster1 = Poster(
            title = "2025 marathon",
            location = "서울",
            startDate = LocalDateTime.of(2025, 5, 17, 10, 0),
            registrationStartDate = LocalDateTime.of(2025, 3, 17, 10, 0),
            registrationEndDate = LocalDateTime.of(2025, 3, 25, 10, 0),
            courses = mutableListOf(course1, course2)
        )

        val poster2 = Poster(
            title = "2025 marathon",
            location = "서울",
            startDate = LocalDateTime.of(2025, 5, 17, 10, 0),
            registrationStartDate = LocalDateTime.of(2025, 3, 17, 10, 0),
            registrationEndDate = LocalDateTime.of(2025, 3, 25, 10, 0),
            courses = mutableListOf(course1, course2)
        )

        posterRepository.save(poster1)
        posterRepository.save(poster2)

        When("전체 조회를 하게 되면") {
            val findAllPoster = posterService.findAllPoster()

            Then("2개의 포스터가 조회 된다.") {
                findAllPoster.size shouldBe 2
            }
        }
    }

    Given("포스터 수정 성공 테스트") {
        val course1 = Course(distance = "42", price = 50000)
        val course2 = Course(distance = "21", price = 35000)

        val poster = Poster(
            title = "2025 marathon",
            location = "서울",
            startDate = LocalDateTime.of(2025, 5, 17, 10, 0),
            registrationStartDate = LocalDateTime.of(2025, 3, 17, 10, 0),
            registrationEndDate = LocalDateTime.of(2025, 3, 25, 10, 0),
            courses = mutableListOf(course1, course2)
        )
        val savedPoster = posterRepository.save(poster)
        val posterId = savedPoster.id!!

        val modifyPosterDTO = ModifyPosterDTO(
            id = posterId,
            posterName = "수정된 포스터 명",
            courses = listOf(
                CourseDTO(distance = "42", price = 50000),
                CourseDTO(distance = "21", price = 35000)
            )
        )

        When("포스터 수정 요청을 하면") {
            val modifyPoster = posterService.modifyPoster(posterId, modifyPosterDTO)
            Then("포스터가 수정 된다.") {
                modifyPoster.title = modifyPosterDTO.posterName
            }
        }
    }

    Given("포스터 수정 실패 테스트") {
        val posterId = "posterId1"
        When("존재하지 않는 포스터 아이디로 수정 요청을 하면") {
            val exception = assertThrows<CustomException> {
                posterService.modifyPoster(posterId, ModifyPosterDTO("11", "11", listOf()))
            }

            Then("404 에러 코드와 상태메시지를 리턴한다.") {
                exception.statusCode shouldBe 404
                exception.errorMessage shouldBe "존재하지 않는 포스터 아이디 입니다. $posterId"                }
        }
    }

})