package com.marathon.marathon.domain.poster.mapper

import com.marathon.marathon.domain.poster.entity.Poster
import com.marathon.marathon.domain.poster.entity.vo.Course
import com.marathon.marathon.domain.poster.entity.vo.PosterStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

class PosterMapperTest : BehaviorSpec({

    Given("도메인이 주어졌을 때") {
        val poster = Poster(
            id = "posterId",
            title = "JTBC 마라톤 대회",
            location = "서울",
            startDate = LocalDateTime.of(2025, 7, 17, 12, 0, 0),
            registrationStartDate = LocalDateTime.of(2025, 5, 17, 12, 0, 0),
            registrationEndDate = LocalDateTime.of(2025, 5, 25, 12, 0, 0),
            status = PosterStatus.UPCOMING,
            courses = mutableListOf(),
        )

        When("DTO 로 변환을 하면 ") {
            val dto = PosterMapper.entityToDTO(poster)

            Then("Response 객체가 반환된다.") {
                dto.id shouldBe poster.id
                dto.title shouldBe poster.title
                dto.location shouldBe poster.location
                dto.startDate shouldBe "2025-07-17"
                dto.startTime shouldBe "12:00:00"
                dto.startDDay shouldBe 57
                dto.registrationStartDate shouldBe "2025-05-17"
                dto.registrationStartDDay shouldBe -3
                dto.registrationEndDate shouldBe "2025-05-25"
                dto.registrationEndDDay shouldBe 4
                dto.status shouldBe "UPCOMING"
                dto.courses.size shouldBe 0
            }
        }
    }
})
