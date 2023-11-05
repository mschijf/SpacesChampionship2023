package scs2023

import java.io.File
import kotlin.math.round

fun main() {
    WrapItUp(useConsole = true).main()
}

class WrapItUp(useConsole: Boolean) {
    private val console = getConsole(useConsole)


    fun main() {
        val width = readln().trim().toInt()
        val height = readln().trim().toInt()
        val depth = readln().trim().toInt()
        val dimensions = Triple(width, height, depth)

        val paper = dimensions.squares().sum() + dimensions.squares().min()
        val ribbon = dimensions.toList().sorted().take(2).sum()*2 + dimensions.volume()*0.1


        println("$paper $ribbon")
    }

    private fun Triple<Int, Int, Int>.squares(): List<Int> {
        val (w, h, d) = this
        return listOf(d*w, d*w, w*h, w*h, d*h, d*h)
    }

    private fun Triple<Int, Int, Int>.volume(): Int {
        val (w, h, d) = this
        return w*h*d
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

