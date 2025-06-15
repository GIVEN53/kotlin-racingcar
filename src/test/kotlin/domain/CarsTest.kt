package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import support.FixedNumberGenerator

class CarsTest {
    @Test
    fun `fails to create Cars when car count is greater than MAX_CAR_COUNT`() {
        // given
        val invalidCarCount = 11
        val carNames = List(invalidCarCount) { "Car$it" }

        // when & then
        shouldThrowExactly<IllegalArgumentException> { Cars(carNames) }
            .message shouldBe "Car count must be less than or equal to 10"
    }

    @Test
    fun `fails to create Cars when car names are not unique`() {
        // given
        val carNames = listOf("Car1", "Car2", "Car1")

        // when & then
        shouldThrowExactly<IllegalArgumentException> { Cars(carNames) }
            .message shouldBe "Car names must be unique"
    }

    @Test
    fun `succeeds in creating Cars when car count is less than or equal to MAX_CAR_COUNT and names are unique`() {
        // given
        val validCarCount = 10
        val carNames = List(validCarCount) { "Car$it" }

        // when
        val cars = Cars(carNames)

        // then
        cars.getCars().map { it.name } shouldBe carNames
    }

    @Test
    fun `moves all cars`() {
        // given
        val carNames = listOf("Car1", "Car2", "Car3")
        val cars = Cars(carNames)
        val numberGenerator = FixedNumberGenerator(4, 5, 6) // All cars will move

        // when
        cars.moveAll(numberGenerator)

        // then
        cars.getCars().forEach { it.position shouldBe 1 }
    }

    @Test
    fun `gets winners when multiple cars have the same maximum position`() {
        // given
        val carNames = listOf("Car1", "Car2", "Car3")
        val cars = Cars(carNames)
        val numberGenerator = FixedNumberGenerator(3, 4, 4) // Two cars will move

        // when
        cars.moveAll(numberGenerator)

        // then
        val winners = cars.getWinners()
        winners.size shouldBe 2
        winners.map { it.name } shouldContainOnly listOf("Car2", "Car3")
    }

    @Test
    fun `gets winners when a single car has the maximum position`() {
        // given
        val carNames = listOf("Car1", "Car2", "Car3")
        val cars = Cars(carNames)
        val numberGenerator = FixedNumberGenerator(5, 3, 3) // Only one car will move

        // when
        cars.moveAll(numberGenerator)

        // then
        val winners = cars.getWinners()
        winners.size shouldBe 1
        winners.first().name shouldBe "Car1"
    }
}
