package com.gmail.jiangyang5157.kotlin_kit.math

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 27, 2017
 */
class VectorD3Test {

    @org.junit.Test
    fun test_constructor() {
        val empty = VectorD3()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)
        assertEquals(0.0, empty.z)

        val oneVal = VectorD3(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)
        assertEquals(1.1, oneVal.z)

        val threeVal = VectorD3(1.0, 2.0, 3.0)
        assertEquals(1.0, threeVal.x)
        assertEquals(2.0, threeVal.y)
        assertEquals(3.0, threeVal.z)
    }

    @org.junit.Test
    fun test_equality() {
        assertEquals(VectorD3(), VectorD3())
        assertNotEquals(VectorD3(), VectorD3(1.1))

        assertTrue(VectorD3() == VectorD3())
        assertTrue(VectorD3() !== VectorD3())
    }

    @org.junit.Test
    fun test_overrider_operator() {
        val a = VectorD3(10.0, 10.0, 10.0)
        val b = VectorD3(-2.0, 2.0, 1.0)

        assertEquals(VectorD3(2.0, -2.0, -1.0), -b)

        assertEquals(VectorD3(8.0, 12.0, 11.0), a + b)
        assertEquals(VectorD3(30.0, 30.0, 30.0), a + 20)
        assertEquals(VectorD3(30.2, 30.2, 30.2), a + 20.2)

        assertEquals(VectorD3(12.0, 8.0, 9.0), a - b)
        assertEquals(VectorD3(-10.0, -10.0, -10.0), a - 20)
        assertEquals(VectorD3(-10.2, -10.2, -10.2), a - 20.2)

        assertEquals(VectorD3(-20.0, 20.0, 10.0), a * b)
        assertEquals(VectorD3(200.0, 200.0, 200.0), a * 20)
        assertEquals(VectorD3(202.0, 202.0, 202.0), a * 20.2)

        assertEquals(VectorD3(-5.0, 5.0, 10.0), a / b)
        assertEquals(VectorD3(0.5, 0.5, 0.5), a / 20)
        assertEquals(VectorD3(1.0, 1.0, 1.0), a / 10)
    }

    @org.junit.Test
    fun test_length() {
        val x = 1.5
        val y = 2.5
        val z = 2.5
        val length = Math.sqrt(x * x + y * y + z * z)
        assertEquals(length, VectorD3(x, y, z).length)
    }

    @org.junit.Test
    fun test_normalize() {
        val x = 1.5
        val y = 2.5
        val z = 2.5
        val length = Math.sqrt(x * x + y * y + z * z)
        val normalize = VectorD3(x / length, y / length, z / length)
        assertEquals(normalize, VectorD3(x, y, z).normalize)
    }

    @org.junit.Test
    fun test_dot() {
        val x1 = 1.5
        val y1 = 2.5
        val z1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val z2 = 4.5
        val dot = x1 * x2 + y1 * y2 + z1 * z2
        assertEquals(dot, VectorD3(x1, y1, z1).dot(VectorD3(x2, y2, z2)))
    }

    @org.junit.Test
    fun test_cross() {
        val x1 = 1.5
        val y1 = 2.5
        val z1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val z2 = 4.5
        val cross = VectorD3(
                y1 * z2 - z1 * y2,
                z1 * x2 - x1 * z2,
                x1 * y2 - y1 * x2
        )
        assertEquals(cross, VectorD3(x1, y1, z1).cross(VectorD3(x2, y2, z2)))
    }

}