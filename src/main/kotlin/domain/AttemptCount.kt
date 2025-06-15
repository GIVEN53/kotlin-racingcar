package domain

private const val MAX_ATTEMPT_COUNT = 10

class AttemptCount(
    count: Int,
) {
    var count: Int = count
        private set

    init {
        require(count > 0) { "Attempt count must be greater than zero." }
        require(count <= MAX_ATTEMPT_COUNT) { "Attempt count must be less than or equal to $MAX_ATTEMPT_COUNT." }
    }

    fun decrement() {
        check(count > 0) { "Attempt count cannot decrement below zero." }
        count--
    }

    fun canAttempt(): Boolean {
        return count > 0
    }
}
