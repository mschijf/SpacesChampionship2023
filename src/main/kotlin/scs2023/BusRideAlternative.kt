package scs2023

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    BusRideAlternative(useConsole = false).main()
}

class BusRideAlternative(useConsole: Boolean) {
    private val console = getConsole(useConsole)

    fun main() {
        val startTime = System.currentTimeMillis()
        testCase()
        val timePassed = System.currentTimeMillis() - startTime
        //print("Time passed (after %d.%03d sec)".format(timePassed / 1000, timePassed % 1000))
    }

    private fun testCase() {
        val numberOfLocations = console.nextLine().toInt()
        val locationList = (1..numberOfLocations).map { Point.of(console.nextLine()) }
        val hq = locationList.first()
        val homeList = locationList.drop(1)

        val answer = homeList.minOfOrNull { node -> shortestRoute(hq, node, homeList.toSet()) + hq.distanceTo(node) } ?: 0
        println(answer)
    }

    private fun shortestRoute(hq: Point, firstNodeToVisit: Point, nodesToVisit:Set<Point>, cache: MutableMap<Pair<Point, Set<Point>>, Int> = mutableMapOf()): Int {
        if (nodesToVisit.size == 1) {
            return hq.distanceTo(firstNodeToVisit)
        }

        val memo = cache[Pair(firstNodeToVisit, nodesToVisit)]
        if (memo != null)
            return memo

        val result = nodesToVisit
            .filter{node -> node != firstNodeToVisit}
            .minOf { node ->
                shortestRoute(hq, node, nodesToVisit-firstNodeToVisit, cache) + firstNodeToVisit.distanceTo(node)
            }

        cache[Pair(firstNodeToVisit, nodesToVisit)] = result
        return result
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

