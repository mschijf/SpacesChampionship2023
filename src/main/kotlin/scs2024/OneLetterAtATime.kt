package scs2024

fun main() {
    val line = readln()
    val domain = line.split(" ").first()
    val ink = line.split(" ").last().toInt()

    val length = domain.length.toDouble()
    val lastPart = length - 1.0 - domain.indexOf('.')
    val saving = ((lastPart / (length - 1.0) )  * ink).toInt()
    println("$saving")
}

