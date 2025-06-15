package domain

import numbergenerator.NumberGenerator
import java.util.Collections

private const val MAX_CAR_COUNT = 10

class Cars(
    carNames: List<String>,
) {
    private val cars: List<Car>

    init {
        require(carNames.size <= MAX_CAR_COUNT) { "Car count must be less than or equal to $MAX_CAR_COUNT" }
        require(carNames.distinct().size == carNames.size) { "Car names must be unique" }
        cars = carNames.map { Car(it) }
    }

    fun moveAll(numberGenerator: NumberGenerator) = cars.forEach { it.move(numberGenerator) }

    fun getWinners(): List<Car> {
        val maxPosition = cars.maxOf { it.position }
        return cars.filter { it.position == maxPosition }
    }

    fun getCars(): List<Car> = Collections.unmodifiableList(cars)
}
