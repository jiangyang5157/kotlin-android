package com.gmail.jiangyang5157.kotlin_kit.math

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 26, 2017
 */
class PointD3Test {

    @org.junit.Test
    fun test_constructor() {
        val empty = PointD3()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)
        assertEquals(0.0, empty.z)

        val oneVal = PointD3(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)
        assertEquals(1.1, oneVal.z)

        val threeVal = PointD3(1.0, 2.0, 3.0)
        assertEquals(1.0, threeVal.x)
        assertEquals(2.0, threeVal.y)
        assertEquals(3.0, threeVal.z)
    }

    @org.junit.Test
    fun test_equality() {
        assertEquals(PointD3(), PointD3())
        assertNotEquals(PointD3(), PointD3(1.1))

        assertTrue(PointD3() == PointD3())
        assertTrue(PointD3() !== PointD3())
    }

}