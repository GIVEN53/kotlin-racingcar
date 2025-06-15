package numbergenerator

import io.kotest.matchers.ints.shouldBeInRange
import org.junit.jupiter.api.Test

class RandomNumberGeneratorTest {
    @Test
    fun `generate returns a number between 0 and 9`() {
        // given
        val numberGenerator = RandomNumberGenerator()

        // when
        val generatedNumber = numberGenerator.generate()

        // then
        generatedNumber shouldBeInRange 0..9
    }
}
