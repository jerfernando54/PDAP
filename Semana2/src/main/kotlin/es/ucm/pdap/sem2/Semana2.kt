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

fun <T> List<T>.split(n: Int): Pair<List<T>, List<T>>  {
    if (this.size == 0) return Nil to Nil
    if (n == 0) return Nil to this
    if (this.size < n) return this to Nil
    tailrec fun splitRec(list: List<T>, cabeza:List<T>, cola:List<T>): Pair<List<T>, List<T>> = when(list) {
        is Nil -> cabeza to cola
        is Cons -> when(cabeza.size) {
            n -> cabeza to cola
            else -> splitRec(list.tail,cabeza + Cons(list.head, Nil), list.tail)
        }
    }
    return splitRec(this, Nil, Nil)
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

fun <T, S> List<Pair<T, S>>.unzip(): Pair<List<T>, List<S>>{
    if (this is Cons){
        tailrec fun unzipRec(xs: List<Pair<T, S>>, lPar: List<T>, lImpar:List<S>): Pair<List<T>, List<S>> = when(xs) {
            is Cons -> unzipRec(xs.tail, lPar + Cons(xs[0].first, Nil), lImpar + Cons(xs[0].second, Nil))
            else -> lPar to lImpar
        }
       return  unzipRec(this, Nil, Nil)
    }
    return Nil to Nil
}

// Ejercicio 10
// ------------

fun <T: Comparable<T>> List<T>.isSorted(): Boolean{
    if (this.size == 0) return true
    tailrec fun isSortedRec(xs: List<T>):Boolean = when(xs) {
        is Nil -> true
        is Cons -> when {
            xs.size < 2 -> true
            xs[0] > xs[1] -> false
            else -> isSortedRec(xs.tail)
        }
    }
    return isSortedRec(this)
}

// Ejercicio 11
// ------------

fun <T> isSubList(xs: List<T>, ys: List<T>): Boolean {
    if (xs.size == 0) return true
    if (ys.size == 0) return false
    tailrec fun isSubListRec(nXs: Int, nYs: Int, flag: Boolean): Boolean = when {
        nXs >= xs.size -> true
        nYs >= ys.size -> false
        xs[nXs] == ys[nYs] -> isSubListRec(nXs + 1, nYs + 1, true)
        flag -> false
        else -> isSubListRec(nXs, nYs + 1, flag)
    }
    return isSubListRec(0, 0, false)
}

