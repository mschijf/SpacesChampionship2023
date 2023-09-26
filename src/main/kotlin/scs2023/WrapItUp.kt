package scs2023

import java.io.File
import kotlin.math.round

fun main() {
    WrapItUp(useConsole = false).main()
}

class WrapItUp(useConsole: Boolean) {
    private val console = getConsole(useConsole)

    private val testLines = console.nextLine().toInt()

    fun main() {
        repeat(testLines) {
            val line = console.nextLine()
            val paper = line.squares().sum() + line.squares().min()
            val ribbon = line.dimensions().sorted().take(2).sum()*2 + round(line.volume()*0.1).toInt()
            println("$paper $ribbon")
        }
    }

    private fun String.dimensions(): List<Int> {
        return this.split(" ").map{it.toInt()}
    }

    private fun String.squares(): List<Int> {
        val (l,w,h) = this.dimensions()
        return listOf(l*w, l*w, w*h, w*h, l*h, l*h)
    }

    private fun String.volume(): Int {
        val (l,w,h) = this.dimensions()
        return l*w*h
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

