package scs2024

import kotlin.math.max
import kotlin.math.min

fun main() {
    val nPlayers = readln().toInt()
    var min = Long.MAX_VALUE
    var max = Long.MIN_VALUE
    repeat(nPlayers) {
        val line =  readln()
        val jumpSize = line.split(" ").first().toInt()
        val nJumps = line.split(" ").last().toInt()
        val sumFast = totalScoreFast(jumpSize, nJumps)
        max = max(max, sumFast)
        min = min(min, sumFast)
    }
    println("$max $min")
}

/**
 * Look at the sum of a serie of 1 + 3 + 5 + 7 + 9 + 11
 * This is equal to
 *              1+11 + 3+9 + 5+7
 *              12   + 12  + 12
 * In other words:
 *                (1 + max) * serieLength
 *                -----------------------
 *                          2
 *
 * with, max = nJumps*jumpSize+1
 *
 */
private fun totalScoreFast(jumpSize: Int, nJumps: Int): Long {
    val n = nJumps.toLong()
    val lastSquare = 1L + jumpSize.toLong() * n
    return (n + 1L) * (1L + lastSquare) / 2L
}

private fun totalScoreSlow(jumpSize: Int, nJumps: Int): Long {
    var sum = 1L
    var square = 1L
    for (i in 1..nJumps) {
        square += jumpSize
        sum += square
    }
    return sum
}


