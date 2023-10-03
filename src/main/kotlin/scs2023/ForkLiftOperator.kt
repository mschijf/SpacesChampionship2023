package scs2023

import java.io.File

fun main() {
    ForkLiftOperator(useConsole = false).main()
}

class ForkLiftOperator(useConsole: Boolean) {
    private val console = getConsole(useConsole)
    private val numberOfActions = console.nextLine().toInt()
    private val actionList = (1..numberOfActions).map{console.nextLine().split("\\s".toRegex()).run{Pair(this[0], this[1].toInt())} }

    fun main() {
        var x = 0
        var y = 0
        var z = 20
        actionList.forEach {
            when(it.first) {
                "EAST" -> if (x + it.second <= 10_000) x += it.second
                "WEST" -> if (x >= it.second) x -= it.second
                "NORTH" -> if (y + it.second <= 10_000) y += it.second
                "SOUTH" -> if (y >= it.second)y -= it.second
                "UP" -> if (z + it.second <= 1_000) z += it.second
                "DOWN" -> if (z >= it.second) z -= it.second
            }
        }
        print("$x x $y x $z = ")
        println(x.toLong() * y.toLong() * z.toLong())
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

