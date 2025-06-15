package view

private const val DEFAULT_DELIMITER = ","

class InputView {
    fun readCarNames(): List<String> {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
        val carNames = readlnOrNull() ?: throw IllegalArgumentException("자동차 이름을 입력하세요.")
        return carNames.split(DEFAULT_DELIMITER).map { it.trim() }
    }

    fun readAttemptCount(): Int {
        println("시도할 회수는 몇회인가요?")
        val attemptCount = readlnOrNull() ?: throw IllegalArgumentException("시도할 회수를 입력하세요.")
        return attemptCount.toIntOrNull() ?: throw IllegalArgumentException("숫자를 입력하세요.")
    }
}
