package scs2023

import java.io.File
import kotlin.math.absoluteValue

val console = java.util.Scanner(System.`in`)
val inputLines = (1..5).map{console.nextLine()}
//val inputLines = getInputLines("data/BusRide", "example")

val hq = Point.of(inputLines.drop(1).first().substringAfter(" "))
val homeList = inputLines.drop(2).map { Point.of(it) }

val allLocations = (homeList + hq)

fun main() {
    println(shortestRoundTrip())
}

fun shortestRoundTrip(from: Point = hq, pathDone: List<Point> = listOf(from), length: Int = 0): Int {
    return if (pathDone.size == allLocations.size) {
//        println("$pathDone ($length) + (${from.distanceTo(hq)}) ")
        from.distanceTo(hq)

    } else {

        allLocations
            .filter { location -> location !in pathDone }
            .minOf { newLocation ->
                shortestRoundTrip(
                    newLocation,
                    pathDone + newLocation,
                    length + from.distanceTo(newLocation)) + from.distanceTo(newLocation)
            }
    }
}

private fun getInputLines(path: String, fileName: String): List<String> {
    val file = File("$path/$fileName")
    return if (file.exists()) file.bufferedReader().readLines() else emptyList()
}

data class Point(val x: Int, val y: Int) {

    fun distanceTo(other: Point) = (other.x - x).absoluteValue + (other.y - y).absoluteValue

    companion object {
        fun of(input: String): Point = input.split("\\s+".toRegex()).run { Point(this[0].toInt(), this[1].toInt()) }
    }
}
