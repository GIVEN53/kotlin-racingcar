package domain

import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import support.FixedNumberGenerator

class CarTest {
    @ParameterizedTest(name = "invalid name: \"{0}\"")
    @ValueSource(strings = ["", " "])
    fun `fails to create a car when name is blank`(invalidName: String) {
        // when & then
        assertThatThrownBy { Car(invalidName) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Car name cannot be blank")
    }

    @Test
    fun `fails to create a car when name is longer than 5 characters`() {
        // given
        val length = 6

        // when & then
        assertThatThrownBy { Car("a".repeat(length)) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Car name must be between 1 and 5 characters")
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 5])
    fun `succeeds in creating a car when name is between 1 and 5 characters`(length: Int) {
        // given
        val validName = "a".repeat(length)

        // when
        val car = Car(validName)

        // then
        assertThat(car.name).isEqualTo(validName)
    }

    @ParameterizedTest
    @ValueSource(ints = [4, 5])
    fun `moves the car when the number generated is greater than or equal to 4`(generatedNumber: Int) {
        // given
        val car = Car("Car")
        val numberGenerator = FixedNumberGenerator(generatedNumber)

        // when
        car.move(numberGenerator)

        // then
        car.position shouldBe 1
    }

    @Test
    fun `does not move the car when the number generated is less than 4`() {
        // given
        val car = Car("Car")
        val numberGenerator = FixedNumberGenerator(3)

        // when
        car.move(numberGenerator)

        // then
        car.position shouldBe 0
    }
}
