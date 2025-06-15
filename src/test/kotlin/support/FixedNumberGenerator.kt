package support

import numbergenerator.NumberGenerator

class FixedNumberGenerator(
    private vararg val fixedNumbers: Int,
) : NumberGenerator {
    private var index = 0

    override fun generate(): Int {
        check(index < fixedNumbers.size) { "No more fixed numbers available" }
        return fixedNumbers[index++]
    }
}
