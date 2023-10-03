package scs2023

import java.io.File
import kotlin.math.round

fun main() {
    WrapItUp(useConsole = true).main()
}

class WrapItUp(useConsole: Boolean) {
    private val console = getConsole(useConsole)


    fun main() {
        val width = console.nextLine().trim().toInt()
        val height = console.nextLine().trim().toInt()
        val depth = console.nextLine().trim().toInt()
        val dimensions = listOf(width, height, depth)

        val paper = dimensions.squares().sum() + dimensions.squares().min()
        val ribbon = dimensions.sorted().take(2).sum()*2 + round(dimensions.volume()*0.1).toInt()
        println("$paper $ribbon")
    }

    private fun List<Int>.squares(): List<Int> {
        val (l,w,h) = this
        return listOf(l*w, l*w, w*h, w*h, l*h, l*h)
    }

    private fun List<Int>.volume(): Int {
        val (l,w,h) = this
        return l*w*h
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

