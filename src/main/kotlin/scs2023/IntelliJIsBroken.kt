package scs2023

import java.io.File


fun main() {
    IntelliJIsBroken(useConsole = true).main()
}

class IntelliJIsBroken(useConsole: Boolean) {
    private val console = getConsole(useConsole)

    private val bracesMap = mapOf('{' to '}', '[' to ']', '(' to ')')

    fun main() {

        val firstLine = console.nextLine().toInt()
        val stringList = (1..firstLine).map{console.nextLine()}

        stringList.forEach {
            if (it.inBalance()) println("YES") else println("NO")
        }
    }

    private fun String.inBalance(): Boolean {
        val stack = ArrayDeque<Char>()
        this.forEach {ch ->
            when (ch) {
                '{', '[', '(' -> {
                    stack.add(ch)
                }
                '}', ']', ')' -> {
                    val top = stack.removeLastOrNull()
                    if (top == null || bracesMap[top] != ch)
                        return false
                }
            }
        }
        return stack.isEmpty()
    }

    private fun getConsole(useConsole: Boolean) =
        if (useConsole)
            java.util.Scanner(System.`in`)
        else
            java.util.Scanner(File("data/${this.javaClass.name.substringAfterLast(".")}/example").bufferedReader())

}

