package scs2023

import java.io.File

fun main() {
    DiscoverStudyBol(useConsole = false).main()
}

class DiscoverStudyBol(useConsole: Boolean) {
    private val console = getConsole(useConsole)
    private val numberOfTracks = console.nextLine().toInt()
    private val trackList = console.nextLine()
        .drop(1).dropLast(1)
        .replace("], ", "]-")
        .split("-")
        .map{it.drop(1).dropLast(1).split(", ")}
        .map{Pair(it[0].toInt(), it[1].toInt())}
        .groupBy ({ it.first}, {it.second })


    fun main() {
        println(if (isCyclic()) "NO" else "YES")
    }

    // Function to check if cycle exists, when walking from startNode
    private fun isCyclicUtil(fromNode: Int,
                             visited: MutableSet<Int>,
                             currentPath: Set<Int> = emptySet()): Boolean {

        if (fromNode in currentPath)
            return true
        if (fromNode in visited)
            return false
        visited += fromNode
        val children = trackList[fromNode] ?: emptyList()
        for (c in children)
            if (isCyclicUtil(c, visited, currentPath+fromNode))
                return true
        return false
    }

    private fun isCyclic(): Boolean {
        val visited = mutableSetOf<Int>()

        //check from each node, if you can make a cycle
        for (fromNode in 0..< numberOfTracks) {
            if (isCyclicUtil(fromNode, visited))
                return true
        }

        return false
    }


    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())
}

