package com.gmail.jiangyang5157.kotlin_math

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 26, 2017
 */
class Point2Test {

    @Test
    fun test_constructor() {
        val empty = Point2()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)

        val oneVal = Point2(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)

        val twoVal = Point2(1.0, 2.0)
        assertEquals(1.0, twoVal.x)
        assertEquals(2.0, twoVal.y)
    }

    @Test
    fun test_equality() {
        assertEquals(Point2(), Point2())
        assertNotEquals(Point2(), Point2(1.1))

        assertTrue(Point2() == Point2())
        assertTrue(Point2() !== Point2())
    }

}