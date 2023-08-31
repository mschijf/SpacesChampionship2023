package tool.collectionspecials

fun main() {
    val ll = (1..10).toLinkedList()
    println(ll)

    var p = ll.firstIndex()
    while (ll.hasPointer(p)) {
        println(ll[p])
        p = p.next(1)
    }


}
