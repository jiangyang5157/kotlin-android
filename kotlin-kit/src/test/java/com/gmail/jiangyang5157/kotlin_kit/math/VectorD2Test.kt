package com.gmail.jiangyang5157.kotlin_kit.math

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 26, 2017
 */
class VectorD2Test {

    @org.junit.Test
    fun test_constructor() {
        val empty = VectorD2()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)

        val oneVal = VectorD2(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)

        val twoVal = VectorD2(1.0, 2.0)
        assertEquals(1.0, twoVal.x)
        assertEquals(2.0, twoVal.y)
    }

    @org.junit.Test
    fun test_equality() {
        assertEquals(VectorD2(), VectorD2())
        assertNotEquals(VectorD2(), VectorD2(1.1))

        assertTrue(VectorD2() == VectorD2())
        assertTrue(VectorD2() !== VectorD2())
    }

    @org.junit.Test
    fun test_overrider_operator() {
        val a = VectorD2(10.0, 10.0)
        val b = VectorD2(-2.0, 2.0)

        assertEquals(VectorD2(2.0, -2.0), -b)

        assertEquals(VectorD2(8.0, 12.0), a + b)
        assertEquals(VectorD2(30.0, 30.0), a + 20)
        assertEquals(VectorD2(30.2, 30.2), a + 20.2)

        assertEquals(VectorD2(12.0, 8.0), a - b)
        assertEquals(VectorD2(-10.0, -10.0), a - 20)
        assertEquals(VectorD2(-10.2, -10.2), a - 20.2)

        assertEquals(VectorD2(-20.0, 20.0), a * b)
        assertEquals(VectorD2(200.0, 200.0), a * 20)
        assertEquals(VectorD2(202.0, 202.0), a * 20.2)

        assertEquals(VectorD2(-5.0, 5.0), a / b)
        assertEquals(VectorD2(0.5, 0.5), a / 20)
        assertEquals(VectorD2(1.0, 1.0), a / 10)
    }

    @org.junit.Test
    fun test_length() {
        val x = 1.5
        val y = 2.5
        val length = Math.sqrt(x * x + y * y)
        assertEquals(length, VectorD2(x, y).length)
    }

    @org.junit.Test
    fun test_normalize() {
        val x = 1.5
        val y = 2.5
        val length = Math.sqrt(x * x + y * y)
        val normalize = VectorD2(x / length, y / length)
        assertEquals(normalize, VectorD2(x, y).normalize)
    }

    @org.junit.Test
    fun test_dot() {
        val x1 = 1.5
        val y1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val dot = x1 * x2 + y1 * y2
        assertEquals(dot, VectorD2(x1, y1).dot(VectorD2(x2, y2)))
    }

    @org.junit.Test
    fun test_cross() {
        val x1 = 1.5
        val y1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val cross = x1 * y2 + y1 * x2
        assertEquals(cross, VectorD2(x1, y1).cross(VectorD2(x2, y2)))
    }

}