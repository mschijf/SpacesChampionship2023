package scs2024

import kotlin.math.absoluteValue
import kotlin.random.Random

fun main() {
    var passwordOk = 0

    val lines = readln().toInt()
    repeat (lines) {
        val line = readln()
        val name = line.split(" ").first()
        val password = line.split(" ").last()
        if (checkPasswordOk(name, password))
            passwordOk++
    }

    println("$passwordOk")

//    createPasswordFile(1000)
}

private fun createPasswordFile(lines: Int) {
    println(lines)
    repeat(lines) {
        val name = createName()
        val pw = if (Random.nextBoolean())
            createPasswordWrong(name)
        else {
            val tmp = createPasswordCorrect(name)
            if (tmp == "") createPasswordWrong(name) else tmp
        }
        println("$name $pw")
    }
}

private fun checkPasswordOk(name: String, password: String) : Boolean {

    var iName = 0
    var iPassword = 0
    while (iPassword < password.length && iName < name.length) {
        if (name[iName] == password[iPassword]) {
            iName++
        }
        iPassword++
    }

    return iName < name.length
}

private fun createName() : String {
    val length = Random.nextInt().absoluteValue % 100
    val bld = StringBuilder()
    repeat(length) {
        val letter = Random.nextInt().absoluteValue % 26
        bld.append('a' + letter)
    }
    return bld.toString()
}

private fun createPasswordWrong(name: String): String {
    val length = Random.nextInt().absoluteValue % 1000
    val bld = StringBuilder()
    var iName = 0
    for (i in 0 .. length) {
        val letter = Random.nextInt().absoluteValue % 26
        bld.append('a' + letter)
        if (iName < name.length && Random.nextBoolean()) {
            bld.append(name[iName])
            iName++
        }
    }
    while (iName < name.length) {
        bld.append(name[iName])
        iName++
    }
    return bld.toString()
}

private fun createPasswordCorrect(name: String): String {
    repeat (10) {
        val length = Random.nextInt().absoluteValue % 1000
        val bld = StringBuilder()
        var iName = 0
        val nn = name.toCharArray()
        nn.shuffle()
        val newName = nn.joinToString("")
        for (i in 0..length) {
            val letter = Random.nextInt().absoluteValue % 26
            bld.append('a' + letter)
            if (iName < newName.length && Random.nextBoolean()) {
                bld.append(newName[iName])
                iName++
            }
        }
        if (checkPasswordOk(name, bld.toString()))
            return bld.toString()
    }
    return ""
}