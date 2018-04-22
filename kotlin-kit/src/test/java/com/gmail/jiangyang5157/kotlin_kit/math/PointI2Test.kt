package com.gmail.jiangyang5157.kotlin_kit.math

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on April 22, 2018
 */
class PointI2Test {

    @Test
    fun test_constructor() {
        val empty = PointI2()
        assertEquals(0, empty.x)
        assertEquals(0, empty.y)

        val oneVal = PointI2(1)
        assertEquals(1, oneVal.x)
        assertEquals(1, oneVal.y)

        val twoVal = PointI2(1, 2)
        assertEquals(1, twoVal.x)
        assertEquals(2, twoVal.y)
    }

    @Test
    fun test_equality() {
        assertEquals(PointI2(), PointI2())
        assertNotEquals(PointI2(), PointI2(1))

        assertTrue(PointI2() == PointI2())
        assertTrue(PointI2() !== PointI2())
    }

}