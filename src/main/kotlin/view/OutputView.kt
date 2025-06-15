package view

import domain.Car

class OutputView {
    fun printResult() {
        println(System.lineSeparator() + "실행 결과")
    }

    fun printPositions(cars: List<Car>) {
        cars.forEach { println("${it.name} : ${"-".repeat(it.position)}") }
        println()
    }

    fun printWinners(winners: List<Car>) {
        println("${winners.joinToString(", ") { it.name }}가 최종 우승했습니다.")
    }
}
