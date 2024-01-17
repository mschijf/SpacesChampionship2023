package scs2024

private const val HUGE = 999_999_999

fun main() {
    val numberOfPackages = readln().toInt()
    val bikeTypes = readln()
        .drop(1).dropLast(1)
        .split(", ")
        .map { it.toInt() }
        .sortedDescending()

    val result = bikeTypes.minimalBikeCount(numberOfPackages)
    if (result >= HUGE)
        println(-1)
    else
        println(result)
}

private fun List<Int>.minimalBikeCount(
    packagesLeft: Int,
    alreadyCalculated: MutableMap<Int, Int> = mutableMapOf()): Int {

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

