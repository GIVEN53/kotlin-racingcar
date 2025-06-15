package domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AttemptCountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `fails to create AttemptCount with zero or negative value`(invalidAttemptCount: Int) {
        // when & then
        shouldThrowExactly<IllegalArgumentException> { AttemptCount(invalidAttemptCount) }
            .message shouldBe "Attempt count must be greater than zero."
    }

    @Test
    fun `fails to create AttemptCount with value greater than MAX_ATTEMPT_COUNT`() {
        // given
        val invalidAttemptCount = 11

        // when & then
        shouldThrowExactly<IllegalArgumentException> { AttemptCount(invalidAttemptCount) }
            .message shouldBe "Attempt count must be less than or equal to 10."
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10])
    fun `succeeds in creating AttemptCount when value is between 1 and MAX_ATTEMPT_COUNT`(validAttemptCount: Int) {
        // when
        val attemptCount = AttemptCount(validAttemptCount)

        // then
        attemptCount.count shouldBe validAttemptCount
    }

    @Test
    fun `decrement the count by one`() {
        // given
        val attemptCount = AttemptCount(5)

        // when
        attemptCount.decrement()

        // then
        attemptCount.count shouldBe 4
    }

    @Test
    fun `fails to decrement the count below zero`() {
        // given
        val attemptCount = AttemptCount(1)
        attemptCount.decrement() // Decrement to zero

        // when & then
        shouldThrowExactly<IllegalStateException> { attemptCount.decrement() }
            .message shouldBe "Attempt count cannot decrement below zero."
    }

    @Test
    fun `returns true when count is greater than zero`() {
        // given
        val attemptCount = AttemptCount(1)

        // when
        val canAttempt = attemptCount.canAttempt()

        // then
        canAttempt shouldBe true
    }

    @Test
    fun `returns false when count is zero`() {
        // given
        val attemptCount = AttemptCount(1)
        attemptCount.decrement() // Decrement to zero

        // when
        val canAttempt = attemptCount.canAttempt()

        // then
        canAttempt shouldBe false
    }
}
