/*
 * -------------------------------------------------------
 * Programación Declarativa Aplicada - Ejercicios semana 1
 * -------------------------------------------------------
 *
 * NOMBRE: José Eduardo Raimundo Fernando
 */

// Completa los ejercicios a continuación. Para comprobar si tu solución es correcta,
// puedes ejecutar los test que se encuentran en la carpeta src/test/kotlin, bien desde
// IntelliJ Idea, o bien mediante `gradle test`

package es.ucm.pdap.sem1

// Ejercicio 2
// -----------

fun pow(n: Int, m: Int): Int = when(m){
    0 -> 1
    1 -> n
    else -> n * pow(n, m-1)
}

// Ejercicio 3
// -----------

fun numDigits(n: Int): Int = when {
    n == 0 -> 0
    n < 10 -> 1
    else -> numDigits(n/10) + 1
}

fun numDigitsTailRec(n: Int): Int {
    tailrec fun numDigitsTailRec2(n2: Int, ac: Int): Int =
        if (n2 == 0) ac else numDigitsTailRec2(n2/10, ac+1)
    return numDigitsTailRec2(n, 0)
}

// Ejercicio 4
// -----------

infix fun Int.concat(other: Int): Int {
    return (pow(10,numDigitsTailRec(other)) * this )+ other
}

// Ejercicio 5
// -----------

tailrec fun <T> List<T>.isPalindrome(): Boolean {
    tailrec fun palindrome(xs: List<T>, i: Int, pos: Int): Boolean {
        if (this.size == 2 && this[0]==this[1] || i >= xs.size)
            return true
        if (this[i] != this[pos])
            return false
        return palindrome(xs,i+1, pos-1)
    }
    return palindrome(this, 0, this.size-1)
}

// Ejercicio 6
// -----------

fun List<Int>.minMax(): Pair<Int, Int>  {
    tailrec fun maxMin(list: List<Int>, acMax: Int, acMin: Int, i: Int): Pair<Int, Int> = when {
        list.isEmpty() -> Integer.MAX_VALUE to Integer.MIN_VALUE
        list.size == i -> acMin to acMax
        list.size == 1 -> list[0] to list[0]
        list[i] >= acMax -> maxMin(list, list[i], acMin, i+1)
        list[i] <= acMin -> maxMin(list, acMax, list[i],i+1)
        else -> maxMin(list, acMax, acMin, i+1)
    }
    return maxMin(this, Integer.MIN_VALUE, Integer.MAX_VALUE , 0)
}

// Ejercicio 7
// -----------

const val EPSILON = 0.0001
fun computeE(): Double {
    fun factorial(n: Int): Double {
        tailrec fun factorialRec(i: Int, ac: Double): Double =
            if (i == 0) ac else factorialRec(i - 1, ac * i)
        return factorialRec(n, 1.0)
    }
    fun computing(n: Int, sum: Double): Double = when {
        1/factorial(n) < EPSILON -> sum + 1/factorial(n)
        else -> computing(n+1, sum + 1/factorial(n))
    }
    return computing(0,0.0)
}
