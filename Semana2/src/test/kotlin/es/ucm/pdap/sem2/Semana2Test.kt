package es.ucm.pdap.sem2

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class Semana2Test {

    @Test
    fun takeTest() {
        val xs = persistentListOf(5, 7, 3, 2)
        assertEquals(persistentListOf<Int>(), xs.take(0))
        assertEquals(persistentListOf(5), xs.take(1))
        assertEquals(persistentListOf(5, 7), xs.take(2))
        assertEquals(persistentListOf(5, 7, 3), xs.take(3))
        assertEquals(persistentListOf(5, 7, 3, 2), xs.take(4))
        assertEquals(persistentListOf(5, 7, 3, 2), xs.take(5))
        assertEquals(persistentListOf(5, 7, 3, 2), xs.take(10))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().take(0))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().take(1))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().take(2))
    }

    @Test
    fun dropTest() {
        val xs = persistentListOf(5, 7, 3, 2)
        assertEquals(persistentListOf(5, 7, 3, 2), xs.drop(0))
        assertEquals(persistentListOf(7, 3, 2), xs.drop(1))
        assertEquals(persistentListOf(3, 2), xs.drop(2))
        assertEquals(persistentListOf(2), xs.drop(3))
        assertEquals(persistentListOf<Int>(), xs.drop(4))
        assertEquals(persistentListOf<Int>(), xs.drop(10))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().drop(0))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().drop(1))
        assertEquals(persistentListOf<Int>(), persistentListOf<Int>().drop(2))
    }

    @Test
    fun splitTest() {
        val xs = persistentListOf(5, 7, 3, 2)
        assertEquals(persistentListOf<Int>() to persistentListOf(5, 7, 3, 2), xs.split(0))
        assertEquals(persistentListOf(5) to persistentListOf(7, 3, 2), xs.split(1))
        assertEquals(persistentListOf(5, 7) to persistentListOf(3, 2), xs.split(2))
        assertEquals(persistentListOf(5, 7, 3) to persistentListOf(2), xs.split(3))
        assertEquals(persistentListOf(5, 7, 3, 2) to persistentListOf<Int>(), xs.split(4))
        assertEquals(persistentListOf(5, 7, 3, 2) to persistentListOf<Int>(), xs.split(10))
        assertEquals(persistentListOf<Int>() to persistentListOf<Int>(), persistentListOf<Int>().split(0))
        assertEquals(persistentListOf<Int>() to persistentListOf<Int>(), persistentListOf<Int>().split(1))
        assertEquals(persistentListOf<Int>() to persistentListOf<Int>(), persistentListOf<Int>().split(2))
    }

    @Test
    fun concatTest() {
        assertEquals(persistentListOf(1, 2, 4, 5, 8, 9, 10),
            persistentListOf(persistentListOf(1, 2), persistentListOf(4, 5), persistentListOf(8, 9, 10)).concat()
        )
        assertEquals(persistentListOf(4, 5, 8, 9, 10),
            persistentListOf(persistentListOf<Int>(),
                persistentListOf(4, 5),
                persistentListOf<Int>(),
                persistentListOf(8, 9, 10),
                persistentListOf<Int>()).concat()
        )
    }

    @Test
    fun zipTest() {
        assertEquals(persistentListOf(1 to 11, 2 to 12, 3 to 13),
            persistentListOf(1, 2, 3) zip persistentListOf(11, 12, 13))
        assertEquals(persistentListOf(1 to 11, 2 to 12, 3 to 13),
            persistentListOf(1, 2, 3) zip persistentListOf(11, 12, 13, 14))
        assertEquals(persistentListOf(1 to 11, 2 to 12, 3 to 13),
            persistentListOf(1, 2, 3, 4) zip persistentListOf(11, 12, 13))
        assertEquals(persistentListOf<Pair<Int, Int>>(),
            persistentListOf<Int>() zip persistentListOf(11, 12, 13))
        assertEquals(persistentListOf<Pair<Int, Int>>(),
            persistentListOf<Int>(1, 2) zip persistentListOf())
        assertEquals(persistentListOf<Pair<Int, Int>>(),
            persistentListOf<Int>() zip persistentListOf())
    }

    @Test
    fun unzipTest() {
        assertEquals(persistentListOf(1, 2, 3) to persistentListOf(4, 5, 6),
            persistentListOf(1 to 4, 2 to 5, 3 to 6).unzip()
        )
        assertEquals(persistentListOf(1, 2) to persistentListOf(4, 5),
            persistentListOf(1 to 4, 2 to 5).unzip()
        )
        assertEquals(persistentListOf(1) to persistentListOf(4),
            persistentListOf(1 to 4).unzip()
        )
        assertEquals(persistentListOf<Int>() to persistentListOf<Int>(),
            persistentListOf<Pair<Int, Int>>().unzip()
        )
    }

    @Test
    fun isSortedTest() {
        assertTrue(persistentListOf(1, 2, 3).isSorted())
        assertFalse(persistentListOf(1, 3, 2).isSorted())
        assertFalse(persistentListOf(2, 1, 3).isSorted())
        assertFalse(persistentListOf(1, 3, 2, 4).isSorted())
        assertTrue(persistentListOf(1, 2, 3, 4).isSorted())
        assertTrue(persistentListOf(1).isSorted())
        assertTrue(persistentListOf<Int>().isSorted())
    }

    @Test
    fun isSubListTest() {
        assertTrue(isSubList(persistentListOf<Int>(), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(1), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(2), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(3), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(1, 2), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(2, 3), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(1, 2, 3), persistentListOf(1, 2, 3)))
        assertTrue(isSubList(persistentListOf<Int>(), persistentListOf<Int>()))
        assertFalse(isSubList(persistentListOf<Int>(1), persistentListOf<Int>()))
        assertFalse(isSubList(persistentListOf<Int>(1), persistentListOf<Int>(2, 4)))
        assertFalse(isSubList(persistentListOf<Int>(1, 2), persistentListOf<Int>(1, 3, 2)))
    }
}