package scs2023

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    BusRide(useConsole = false).main()
}

class BusRide(useConsole: Boolean) {
    private val console = getConsole(useConsole)
    private val numberOfTests = console.nextLine().toInt()

    fun main() {
        repeat(numberOfTests) {
            testCase()
        }
    }

    private fun testCase() {
        val firstLine = console.nextLine()
        val numberOfHouses = firstLine.substringBefore(" ").toInt()
        val inputLines = (1..numberOfHouses).map { console.nextLine() }

        val hq = Point.of(firstLine.substringAfter(" "))
        val homeList = inputLines.map { Point.of(it) }

        val allocations = (homeList + hq)

        println(allocations.shortestRoundTrip(from = hq))
    }


    private fun List<Point>.shortestRoundTrip(
        from: Point,
        final: Point = from,
        pathDone: List<Point> = listOf(from),
        length: Int = 0
    ): Int {
        return if (pathDone.size == this.size) {
            from.distanceTo(final)

        } else {

            this
                .filter { location -> location !in pathDone }
                .minOf { newLocation ->
                    shortestRoundTrip(
                        newLocation,
                        final,
                        pathDone + newLocation,
                        length + from.distanceTo(newLocation)
                    ) + from.distanceTo(newLocation)
                }
        }
    }

    data class Point(val x: Int, val y: Int) {

        fun distanceTo(other: Point) = (other.x - x).absoluteValue + (other.y - y).absoluteValue

        companion object {
            fun of(input: String): Point = input.split("\\s+".toRegex()).run { Point(this[0].toInt(), this[1].toInt()) }
        }
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

