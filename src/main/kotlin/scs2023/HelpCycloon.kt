package scs2023

import java.io.File

fun main() {
    HelpCycloon(useConsole = false).main()
}

private const val HUGE = 999_999_999

class HelpCycloon(useConsole: Boolean) {
    private val console = getConsole(useConsole)
    private val numberOfPackages = console.nextLine().toInt()
    private val bikeTypes = console.nextLine()
        .drop(1).dropLast(1)
        .split(", ".toRegex())
        .map{it.toInt()}
        .sortedDescending()


    private fun List<Int>.minimalBikeCount(packagesLeft: Int, alreadyCalculated: MutableMap<Int, Int> = mutableMapOf()): Int {
        if (packagesLeft == 0)
            return 0

        if (alreadyCalculated.contains(packagesLeft))
            return alreadyCalculated[packagesLeft]!!

        var minimalBikesNeeded = HUGE
        this.filter{it <= packagesLeft}.forEach { bikeType ->
            val tmp = 1 + this.minimalBikeCount(packagesLeft - bikeType, alreadyCalculated)
            if (tmp < minimalBikesNeeded)
                minimalBikesNeeded = tmp
        }
        alreadyCalculated[packagesLeft] = minimalBikesNeeded
        return minimalBikesNeeded
    }

    fun main() {
        val result = bikeTypes.minimalBikeCount(numberOfPackages)
        if (result >= HUGE)
            println(-1)
        else
            println(result)
    }


    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

