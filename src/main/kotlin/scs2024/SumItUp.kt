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

private fun totalScoreFast(jumpSize: Int, nJumps: Int): Long {
    return ((nJumps.toLong() + 1L) * (2L + jumpSize.toLong()*nJumps.toLong()) / 2L)
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


