package scs2023

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.round

fun main() {
    BusRideAlternative(useConsole = false).main()
}

class BusRideAlternative(useConsole: Boolean) {
    private val console = getConsole(useConsole)

    fun main() {

        for (volume in 0..9) {
            val res = round(1 + (volume * 0.1) ).toInt()
            println("${volume} $res")
        }


//        val startTime = System.currentTimeMillis()
//        testCase()
//        val timePassed = System.currentTimeMillis() - startTime
//        //print("Time passed (after %d.%03d sec)".format(timePassed / 1000, timePassed % 1000))
    }

    private fun testCase() {
        val numberOfLocations = console.nextLine().toInt()
        val locationList = (1..numberOfLocations).map { Point.of(console.nextLine()) }
        val hq = locationList.first()
        val homeList = locationList.drop(1)

        val answer = shortestRoute(hq, hq, homeList.toSet())
        println(answer)
    }

    private val cache: MutableMap<Pair<Point, Set<Point>>, Int> = mutableMapOf()

    private fun shortestRoute(hq: Point, currentNode: Point, nodesToVisit:Set<Point>): Int {
        if (nodesToVisit.isEmpty()) {
            return hq.distanceTo(currentNode)
        }

        val memo = cache[Pair(currentNode, nodesToVisit)]
        if (memo != null)
            return memo

        val result = nodesToVisit
            .minOf { node ->
                shortestRoute(hq, node, nodesToVisit-node) + currentNode.distanceTo(node)
            }

        cache[Pair(currentNode, nodesToVisit)] = result
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

