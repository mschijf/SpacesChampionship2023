package scs2023

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    BusRideAlternative(useConsole = false).main()
}

class BusRideAlternative(useConsole: Boolean) {
    private val console = getConsole(useConsole)
    private val numberOfTests = console.nextLine().toInt()

    fun main() {
        repeat(numberOfTests) {
            val startTime = System.currentTimeMillis()
            testCase()
            val timePassed = System.currentTimeMillis() - startTime
            //print("Time passed (after %d.%03d sec)".format(timePassed / 1000, timePassed % 1000))
        }
    }

    private fun testCase() {
        val firstLine = console.nextLine()
        val numberOfHouses = firstLine.substringBefore(" ").toInt()
        val hq = Point.of(firstLine.substringAfter(" "))
        val homeList = (1..numberOfHouses).map { Point.of(console.nextLine()) }

        val answer = homeList.minOf { node -> shortestRoute(hq, node, homeList.toSet()) + hq.distanceTo(node) }
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

