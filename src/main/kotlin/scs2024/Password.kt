package scs2024

fun main() {
    var passwordOk = 0

    repeat (3) {
        val line = readln()
        val name = line.split(" ").first()
        val password = line.split(" ").last()
        if (checkPasswordOk(name, password))
            passwordOk++
    }

    println("$passwordOk")
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