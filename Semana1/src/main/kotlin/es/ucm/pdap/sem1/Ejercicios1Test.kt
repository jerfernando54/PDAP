package es.ucm.pdap.sem1

import java.lang.Math.floor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Ejercicios1Test {
    @Test
    fun testPow() {
        assertEquals(8, pow(2, 3))
        assertEquals(16, pow(2, 4))
        assertEquals(1, pow(1, 10))
        assertEquals(243, pow(3, 5))
        assertEquals(4, pow(4, 1))
        assertEquals(1, pow(1, 1))
        assertEquals(1, pow(3, 0))
        assertEquals(0, pow(0, 4))
    }

    @Test
    fun testNumDigits() {
        assertEquals(3, numDigits(453))
        assertEquals(2, numDigits(23))
        assertEquals(1, numDigits(1))
        assertEquals(5, numDigits(10000))
        assertEquals(3, numDigitsTailRec(453))
        assertEquals(2, numDigitsTailRec(23))
        assertEquals(1, numDigitsTailRec(1))
        assertEquals(5, numDigitsTailRec(10000))
    }

    @Test
    fun testConcat() {
        assertEquals(14256, 1 concat 4256)
        assertEquals(14256, 14 concat 256)
        assertEquals(14256, 142 concat 56)
        assertEquals(14256, 1425 concat 6)

        assertEquals(8475, 8475 concat 0)
    }


    @Test
    fun testIsPalindrome() {
        assertTrue(listOf(1, 4, 5, 4, 1).isPalindrome())
        assertFalse(listOf(1, 3, 5, 4, 1).isPalindrome())
        assertTrue(listOf(1).isPalindrome())
        assertTrue(listOf(1, 1).isPalindrome())
        assertFalse(listOf(1, 2).isPalindrome())
        assertFalse(listOf(1, 2, 3).isPalindrome())

        // Si no se utiliza recursión de cola, el siguiente ejemplo debería
        // fallar por StackOverflowError
        val megaLista = (1 until 10000).toList() + (10000 downTo 1).toList()
        assertTrue(megaLista.isPalindrome())
    }

    @Test
    fun testMinMax() {
        // Recuerda: "-4 to 10" equivale a Pair(-4, 10)
        assertEquals(-4 to 10, listOf(1, 6, 3, 2, -4, 10, 7).minMax())
        assertEquals(-4 to 20, listOf(20, 6, 3, 2, -4, 10, 7).minMax())
        assertEquals(-9 to 10, listOf(3, 6, 3, 2, -4, 10, -9).minMax())
        assertEquals(3 to 3, listOf(3).minMax())
        assertEquals(Integer.MAX_VALUE to Integer.MIN_VALUE, listOf<Int>().minMax())

        // Si no se utiliza recursión de cola, el siguiente ejemplo debería
        // fallar por StackOverflowError
        val megaLista = (1 until 10000).toList() + (10000 downTo 1).toList()
        assertEquals(1 to 10000, megaLista.minMax())
    }

    @Test
    fun computeETest() {
        assertEquals(2.718, floor(computeE() * 1000) / 1000.0)
    }
}