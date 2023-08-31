package scs2023

import tool.coordinate.twodimensional.Point
import java.io.File

val console = java.util.Scanner(System.`in`)
val inputLines = (1..5).map{console.nextLine()}
//val inputLines = getInputLines("data/BusRide", "example")

val hq = Point.of(gridOrientation = false, inputLines.drop(1).first().substringAfter(" "))
val homeList = inputLines.drop(2).map { Point.of(gridOrientation = false, it) }

val allLocations = (homeList + hq)

fun main() {
    println(shortestPathToAll(backToStart = true))
}

fun shortestPathToAll(from: Point = hq, backToStart: Boolean = false, pathDone: List<Point> = listOf(from), length: Int = 0): Int {
    return if (pathDone.size == allLocations.size) {
//        println("$pathDone ($length) + (${from.distanceTo(hq)}) ")
        if (backToStart) from.distanceTo(hq) else 0

    } else {

        allLocations.filter { location -> location !in pathDone }.minOf { newLocation ->
            shortestPathToAll(
                newLocation,
                backToStart,
                pathDone + newLocation,
                length + from.distanceTo(newLocation)
            ) + from.distanceTo(newLocation)
        }

    }
}

private fun getInputLines(path: String, fileName: String): List<String> {
    val file = File("$path/$fileName")
    return if (file.exists()) file.bufferedReader().readLines() else emptyList()
}

