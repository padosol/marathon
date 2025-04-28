package com.marathon.marathon.service

import com.marathon.marathon.dto.request.CourseDTO
import com.marathon.marathon.dto.request.CreatePosterDTO
import com.marathon.marathon.dto.request.ModifyPosterDTO
import com.marathon.marathon.entity.Course
import com.marathon.marathon.entity.Poster
import com.marathon.marathon.exception.CustomException
import com.marathon.marathon.repository.PosterRepository
import com.marathon.marathon.service.usecase.GetPosterUseCase
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus

class PosterServiceTest : BehaviorSpec({

 val posterRepository = mockk<PosterRepository>()
 val posterService = PosterService(posterRepository)

 // 테스트 데이터 설정
 val courses = mutableListOf(
  Course(
   courseName = "마라톤 10km",
   courseType = "RUNNING",
   prise = 10000
  ),
  Course(
   courseName = "마라톤 5km",
   courseType = "WALKING",
   prise = 5000
  )
 )

 val samplePoster = Poster(
  posterName = "서울 마라톤 2023",
  courses = courses.toMutableList()
 )

 val courseDTOs = listOf(
  CourseDTO(
   courseName = "마라톤 10km",
   courseType = "RUNNING",
   price = 10000
  ),
  CourseDTO(
   courseName = "마라톤 5km",
   courseType = "WALKING",
   price = 5000
  )
 )

 val createPosterDTO = CreatePosterDTO(
  posterName = "서울 마라톤 2023",
  courses = courseDTOs
 )

 val modifyPosterDTO = ModifyPosterDTO(
  id = "posterId",
  posterName = "서울 마라톤 2024",
  courses = courseDTOs
 )

 beforeTest {
  clearAllMocks()
 }

 given("포스터 ID로 포스터를 조회할 때") {
  val posterId = "poster123"

  `when`("존재하는 포스터 ID인 경우") {
   every { posterRepository.findById(posterId) } returns samplePoster

   then("해당 포스터가 반환되어야 한다") {
    val result = posterService.findPosterById(posterId)

    result shouldBe samplePoster
    result.posterName shouldBe "서울 마라톤 2023"
    result.courses.size shouldBe 2

    verify(exactly = 1) { posterRepository.findById(posterId) }
   }
  }

  `when`("존재하지 않는 포스터 ID인 경우") {
   val nonExistentId = "nonexistent"
   every { posterRepository.findById(nonExistentId) } returns null

   then("CustomException이 발생해야 한다") {
    val exception = shouldThrow<CustomException> {
     posterService.findPosterById(nonExistentId)
    }

    exception.statusCode shouldBe HttpStatus.NOT_FOUND.value()
    exception.errorMessage shouldContain "존재하지 않는 포스터 아이디"

    verify(exactly = 1) { posterRepository.findById(nonExistentId) }
   }
  }
 }

 given("모든 포스터를 조회할 때") {
  val posters = listOf(
   Poster("1", "서울 마라톤 2023", courses.toMutableList()),
   Poster("2", "부산 마라톤 2023", courses.toMutableList())
  )

  `when`("포스터가 존재하는 경우") {
   every { posterRepository.findAll() } returns posters

   then("모든 포스터 목록이 반환되어야 한다") {
    val result = posterService.findAllPoster()

    result.size shouldBe 2
    result[0].posterName shouldBe "서울 마라톤 2023"
    result[1].posterName shouldBe "부산 마라톤 2023"

    verify(exactly = 1) { posterRepository.findAll() }
   }
  }
 }

 given("새로운 포스터를 생성할 때") {
  `when`("유효한 데이터가 제공되는 경우") {
   every { posterRepository.save(any()) } answers { firstArg() }

   then("새로운 포스터가 생성되고 반환되어야 한다") {
    val result = posterService.createPoster(createPosterDTO)

    result.posterName shouldBe "서울 마라톤 2023"
    result.courses.size shouldBe 2
    result.courses[0].courseName shouldBe "마라톤 10km"
    result.courses[0].courseType shouldBe "RUNNING"
    result.courses[0].prise shouldBe 10000

    verify(exactly = 1) { posterRepository.save(any()) }
   }
  }
 }

 given("기존 포스터를 수정할 때") {
  val posterId = "poster123"

  `when`("존재하는 포스터 ID인 경우") {
   every { posterRepository.findById(posterId) } returns samplePoster
   every { posterRepository.save(any()) } answers { firstArg() }

   then("포스터가 수정되고 반환되어야 한다") {
    val result = posterService.modifyPoster(posterId, modifyPosterDTO)

    result.posterName shouldBe "서울 마라톤 2024" // 수정된 이름 확인

    verify(exactly = 1) { posterRepository.findById(posterId) }
    verify(exactly = 1) { posterRepository.save(any()) }
   }
  }

  `when`("존재하지 않는 포스터 ID인 경우") {
   val nonExistentId = "nonexistent"
   every { posterRepository.findById(nonExistentId) } returns null

   then("CustomException이 발생해야 한다") {
    val exception = shouldThrow<CustomException> {
     posterService.modifyPoster(nonExistentId, modifyPosterDTO)
    }

    exception.statusCode shouldBe HttpStatus.NOT_FOUND.value()
    exception.errorMessage shouldContain "존재하지 않는 포스터 아이디"

    verify(exactly = 1) { posterRepository.findById(nonExistentId) }
    verify(exactly = 0) { posterRepository.save(any()) }
   }
  }
 }

 given("포스터를 삭제할 때") {
  val posterId = "poster123"

  `when`("존재하는 포스터 ID인 경우") {
   every { posterRepository.findById(posterId) } returns samplePoster
   every { posterRepository.deletePoster(posterId) } returns Unit

   then("포스터가 성공적으로 삭제되어야 한다") {
    posterService.removePoster(posterId)

    verify(exactly = 1) { posterRepository.findById(posterId) }
    verify(exactly = 1) { posterRepository.deletePoster(posterId) }
   }
  }

  `when`("존재하지 않는 포스터 ID인 경우") {
   val nonExistentId = "nonexistent"
   every { posterRepository.findById(nonExistentId) } returns null

   then("CustomException이 발생해야 한다") {
    val exception = shouldThrow<CustomException> {
     posterService.removePoster(nonExistentId)
    }

    exception.statusCode shouldBe HttpStatus.NOT_FOUND.value()
    exception.errorMessage shouldContain "존재하지 않는 포스터 아이디"

    verify(exactly = 1) { posterRepository.findById(nonExistentId) }
    verify(exactly = 0) { posterRepository.deletePoster(any()) }
   }
  }
 }
})
