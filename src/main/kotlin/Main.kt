import controller.RacingController
import numbergenerator.RandomNumberGenerator
import view.InputView
import view.OutputView

fun main(args: Array<String>) {
    val racingController = RacingController(InputView(), OutputView(), RandomNumberGenerator())
    racingController.start()
}
