package domain

class Car(
    val name: String,
) {
    var position: Int = 0

    init {
        require(name.isNotBlank()) { "Car name cannot be blank" }
        require(
            name.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH,
        ) { "Car name must be between $MIN_NAME_LENGTH and $MAX_NAME_LENGTH characters" }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 1
        private const val MAX_NAME_LENGTH = 5
    }
}
