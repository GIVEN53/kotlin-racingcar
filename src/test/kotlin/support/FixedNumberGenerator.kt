package support

import numbergenerator.NumberGenerator

class FixedNumberGenerator(
    private val fixedNumber: Int,
) : NumberGenerator {
    override fun generate(): Int {
        return fixedNumber
    }
}
