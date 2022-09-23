/*
 * -------------------------------------------------------
 * Programación Declarativa Aplicada - Ejercicios semana 2
 * -------------------------------------------------------
 *
 * NOMBRE: José Eduardo Raimundo Fernando
 */

// Completa los ejercicios a continuación. Para comprobar si tu solución es correcta,
// puedes ejecutar los test que se encuentran en la carpeta src/test/kotlin, bien desde
// IntelliJ Idea, o bien mediante `gradle test`


package es.ucm.pdap.sem2

//3- quitar, tampoco hay que entregarlo
operator fun <T> List<T>.get(i: Int): T = when(this){
    is Nil -> throw IndexOutOfBoundsException()
    is Cons -> when {
        i == 0 -> this.head
        else -> this.tail.get(i-1)
    }
}

// Implementación de listas, con atributo `size` y método `toString()`

sealed class List<out T>(val size: Int)

object Nil : List<Nothing>(0) {
    override fun toString(): String = "[]"
}
data class Cons<T>(val head: T, val tail: List<T>) : List<T>(1 + tail.size) {
    override fun toString(): String {
        fun toStringRec(l: List<T>): String = when (l) {
            is Nil -> ""
            is Cons -> ", " + l.head.toString() + toStringRec(l.tail)
        }
        return "[" + head + toStringRec(tail) + "]"
    }
}

// Operaciones elementales sobre listas

operator fun <T> List<T>.plus(other: List<T>): List<T> = when (this) {
    is Nil -> other
    is Cons -> Cons(head, tail + other)
}


fun <T> persistentListOf(vararg elems: T): List<T> {
    tailrec fun persistentListOfRec(i: Int, ac: List<T>): List<T> = when {
        i < 0 -> ac
        else -> persistentListOfRec(i - 1, Cons(elems[i], ac))
    }
    return persistentListOfRec(elems.size - 1, Nil)
}

// Ejercicio 4
// -----------

fun <T> List<T>.take(n: Int): List<T> = when {
    this.size <= n -> this
    n <= 0 -> Nil
    else -> when(this) {
        is Nil -> Nil
        is Cons -> Cons(this.head, tail.take(n-1))
    }
}

// Ejercicio 5
// -----------

fun <T> List<T>.drop(n: Int): List<T> = when {
    n >= this.size -> Nil
    n <= 0 -> this
    else -> when(this) {
        is Nil -> Nil
        is Cons -> tail.drop(n-1)
    }
}

// Ejercicio 6
// -----------

fun <T> List<T>.split(n: Int): Pair<List<T>, List<T>> = when {
    n == 0 -> Nil to this
    n > this.size -> this to Nil
    else -> when(this) {
        is Nil -> Nil to Nil
        is Cons -> Cons(head, tail.take(n-1)) to tail.drop(n-1)
    }
}

// Ejercicio 7
// -----------

fun <T> List<List<T>>.concat(): List<T> = when {
    this.size == 0 -> Nil
    else -> when(this) {
        is Nil -> Nil
        is Cons -> head + tail.concat()
    }
}

// Ejercicio 8
// -----------

infix fun <T, S> List<T>.zip(other: List<S>): List<Pair<T, S>> = when {
    this.size == 0 || other.size == 0 -> Nil
    this.size < other.size -> when(this){
        is Nil -> Nil
        is Cons -> when(other) {
            is Nil -> Nil
            is Cons -> Cons(head to other.head, tail.zip(other.tail))
        }
    }
    else -> when(other) {
        is Nil -> Nil
        is Cons -> when(this) {
            is Nil -> Nil
            is Cons -> Cons(head to other.head, tail.zip(other.tail))
        }
    }
}

// Ejercicio 9
// -----------

fun <T, S> List<Pair<T, S>>.unzip(): Pair<List<T>, List<S>> = TODO()

// Ejercicio 10
// ------------

fun <T: Comparable<T>> List<T>.isSorted(): Boolean = when{
    this.size <= 0 -> true
    else -> when(this) {
        is Nil -> true
        is Cons -> if (head < ) this.tail.isSorted()
                        else false
    }

}

// Ejercicio 11
// ------------

fun <T> isSubList(xs: List<T>, ys: List<T>): Boolean = TODO()

// Ejercicio 12
fun <T: Comparable<T>> List<T>.insertOrd(elem: T): List<T> = when(this) {
    is Nil -> Cons(elem, Nil)
    is Cons -> if(elem <= this.head) Cons(elem, this)
                    else Cons(head, this.tail.insertOrd(elem))
}

fun <T: Comparable<T>> List<T>.iSort(): List<T> = when(this) {
    is Nil -> Nil
    is Cons -> tail.iSort().insertOrd(head)
}

// Ejercicio 13
//13- a
infix fun <T: Comparable<T>> List<T>.merge(other: List<T>): List<T> = when(this) {
    is Nil -> other
    is Cons -> when(other) {
        is Nil -> this
        is Cons -> if (head <= other.head) Cons(head, this.tail merge other)
                        else Cons(other.head, this merge other.tail)
    }
}

//13- b
fun <T: Comparable<T>> List<T>.mergeSort(): List<T> = when {
    this.size <=1 -> this
    else -> {
        val (xs1, xs2) = this.split(this.size / 2)
        xs1.mergeSort() merge xs2.mergeSort()
    }
}



fun main() {
    val l = persistentListOf(1, 5, 3)
    print(l.insertOrd(4))
}