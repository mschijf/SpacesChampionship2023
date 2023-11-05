package scs2023

fun main() {
    val numberOfActions = readlnOrNull()!!.toInt()
    val actionList: List<Pair<String, Int>> =
        (1..numberOfActions)
            .map {
                readlnOrNull()!!
                    .split("\\s".toRegex())
                    .let { lineParts -> Pair(lineParts[0], lineParts[1].toInt()) }
            }

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
    println(x.toLong() * y.toLong() * z.toLong())
}
