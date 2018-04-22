package com.gmail.jiangyang5157.kotlin_kit.math

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on April 22, 2018
 */
class PointI3Test {

    @org.junit.Test
    fun test_constructor() {
        val empty = PointI3()
        assertEquals(0, empty.x)
        assertEquals(0, empty.y)
        assertEquals(0, empty.z)

        val oneVal = PointI3(1)
        assertEquals(1, oneVal.x)
        assertEquals(1, oneVal.y)
        assertEquals(1, oneVal.z)

        val threeVal = PointI3(1, 2, 3)
        assertEquals(1, threeVal.x)
        assertEquals(2, threeVal.y)
        assertEquals(3, threeVal.z)
    }

    @org.junit.Test
    fun test_equality() {
        assertEquals(PointI3(), PointI3())
        assertNotEquals(PointI3(), PointI3(1))

        assertTrue(PointI3() == PointI3())
        assertTrue(PointI3() !== PointI3())
    }

}