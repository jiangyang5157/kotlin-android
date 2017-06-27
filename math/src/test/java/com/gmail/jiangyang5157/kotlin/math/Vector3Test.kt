package com.gmail.jiangyang5157.kotlin.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yang Jiang on June 27, 2017
 */
class Vector3Test {

    @Test
    fun test_constructor() {
        val empty = Vector3()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)
        assertEquals(0.0, empty.z)

        val oneVal = Vector3(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)
        assertEquals(1.1, oneVal.z)

        val threeVal = Vector3(1.0, 2.0, 3.0)
        assertEquals(1.0, threeVal.x)
        assertEquals(2.0, threeVal.y)
        assertEquals(3.0, threeVal.z)
    }

    @Test
    fun test_overrider_operator() {
        val a = Vector3(10.0, 10.0, 10.0)
        val b = Vector3(-2.0, 2.0, 1.0)

        assertEquals(Vector3(2.0, -2.0, -1.0), -b)

        assertEquals(Vector3(8.0, 12.0, 11.0), a + b)
        assertEquals(Vector3(30.0, 30.0, 30.0), a + 20)
        assertEquals(Vector3(30.2, 30.2, 30.2), a + 20.2)

        assertEquals(Vector3(12.0, 8.0, 9.0), a - b)
        assertEquals(Vector3(-10.0, -10.0, -10.0), a - 20)
        assertEquals(Vector3(-10.2, -10.2, -10.2), a - 20.2)

        assertEquals(Vector3(-20.0, 20.0, 10.0), a * b)
        assertEquals(Vector3(200.0, 200.0, 200.0), a * 20)
        assertEquals(Vector3(202.0, 202.0, 202.0), a * 20.2)

        assertEquals(Vector3(-5.0, 5.0, 10.0), a / b)
        assertEquals(Vector3(0.5, 0.5, 0.5), a / 20)
        assertEquals(Vector3(1.0, 1.0, 1.0), a / 10)
    }

    @Test
    fun test_length() {
        val x = 1.5
        val y = 2.5
        val z = 2.5
        val length = Math.sqrt(x * x + y * y + z * z)
        assertEquals(Vector3(x, y, z).length, length)
    }

    @Test
    fun test_normalize() {
        val x = 1.5
        val y = 2.5
        val z = 2.5
        val length = Math.sqrt(x * x + y * y + z * z)
        val normalize = Vector3(x / length, y / length, z / length)
        assertEquals(Vector3(x, y, z).normalize, normalize)
    }

    @Test
    fun test_dot() {
        val x1 = 1.5
        val y1 = 2.5
        val z1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val z2 = 4.5
        val dot = x1 * x2 + y1 * y2 + z1 * z2
        assertEquals(Vector3(x1, y1, z1).dot(Vector3(x2, y2, z2)), dot)
    }

    @Test
    fun test_cross() {
        val x1 = 1.5
        val y1 = 2.5
        val z1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val z2 = 4.5
        val cross = Vector3(
                y1 * z2 - z1 * y2,
                z1 * x2 - x1 * z2,
                x1 * y2 - y1 * x2
        )
        assertEquals(Vector3(x1, y1, z1).cross(Vector3(x2, y2, z2)), cross)
    }

}