package scs2023

val bracesMap = mapOf('{' to '}', '[' to ']', '(' to ')')

fun main() {
    val console = java.util.Scanner(System.`in`)
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
                if (top != null && bracesMap[top] != ch)
                    return false
            }
        }
    }
    return stack.isEmpty()
}
