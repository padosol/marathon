package com.marathon.marathon.domain.poster.mapper

import com.marathon.marathon.domain.poster.dto.request.CourseDTO
import com.marathon.marathon.domain.poster.dto.response.CourseResponse
import com.marathon.marathon.domain.poster.entity.vo.Course
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CourseMapperTest : BehaviorSpec({

    Given("도메인이 주어질 때") {
        val course = Course(
            distance = "42.195",
            price = 50000
        )

        val courseDto = CourseDTO(
            distance = "42.195",
            price = 50000
        )

        When("domainToResponse 를 실행하면") {
            val response = CourseMapper.domainToResponse(course)

            Then("Response 로 변환한다.") {
                response.distance shouldBe course.distance
                response.price shouldBe  course.price
            }
        }

        When("responseToDomain 을 실행하면") {
            val domain = CourseMapper.dtoToDomain(courseDto)
            Then("Domain 이 반환된다.") {
                domain.distance shouldBe courseDto.distance
                domain.price shouldBe  courseDto.price
            }
        }
    }

})
