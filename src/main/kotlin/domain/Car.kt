package domain

import numbergenerator.NumberGenerator

private const val MIN_NAME_LENGTH = 1
private const val MAX_NAME_LENGTH = 5
private const val MOVE_THRESHOLD = 4

class Car(
    val name: String,
) {
    var position: Int = 0
        private set

    init {
        require(name.isNotBlank()) { "Car name cannot be blank" }
        require(
            name.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH,
        ) { "Car name must be between $MIN_NAME_LENGTH and $MAX_NAME_LENGTH characters" }
    }

    fun move(numberGenerator: NumberGenerator) {
        if (numberGenerator.generate() >= MOVE_THRESHOLD) {
            position++
        }
    }
}
