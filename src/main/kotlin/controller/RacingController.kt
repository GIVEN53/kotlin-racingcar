package controller

import domain.AttemptCount
import domain.Cars
import numbergenerator.NumberGenerator
import view.InputView
import view.OutputView

class RacingController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val numberGenerator: NumberGenerator,
) {
    fun start() {
        val cars = getCars()
        val attemptCount = getAttemptCount()

        outputView.printResult()
        while (attemptCount.canAttempt()) {
            attemptCount.decrement()
            cars.moveAll(numberGenerator)
            outputView.printPositions(cars.getCars())
        }

        outputView.printWinners(cars.getWinners())
    }

    private fun getCars(): Cars {
        val carNames = inputView.readCarNames()
        return Cars(carNames)
    }

    private fun getAttemptCount(): AttemptCount {
        val attemptCount = inputView.readAttemptCount()
        return AttemptCount(attemptCount)
    }
}
