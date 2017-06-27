package com.gmail.jiangyang5157.kotlin.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yang Jiang on June 26, 2017
 */
class Vector2Test {

    @Test
    fun test_constructor() {
        val empty = Vector2()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)

        val oneVal = Vector2(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)

        val twoVal = Vector2(1.0, 2.0)
        assertEquals(1.0, twoVal.x)
        assertEquals(2.0, twoVal.y)
    }

    @Test
    fun test_overrider_operator() {
        val a = Vector2(10.0, 10.0)
        val b = Vector2(-2.0, 2.0)

        assertEquals(Vector2(2.0, -2.0), -b)

        assertEquals(Vector2(8.0, 12.0), a + b)
        assertEquals(Vector2(30.0, 30.0), a + 20)
        assertEquals(Vector2(30.2, 30.2), a + 20.2)

        assertEquals(Vector2(12.0, 8.0), a - b)
        assertEquals(Vector2(-10.0, -10.0), a - 20)
        assertEquals(Vector2(-10.2, -10.2), a - 20.2)

        assertEquals(Vector2(-20.0, 20.0), a * b)
        assertEquals(Vector2(200.0, 200.0), a * 20)
        assertEquals(Vector2(202.0, 202.0), a * 20.2)

        assertEquals(Vector2(-5.0, 5.0), a / b)
        assertEquals(Vector2(0.5, 0.5), a / 20)
        assertEquals(Vector2(1.0, 1.0), a / 10)
    }

    @Test
    fun test_length() {
        val x = 1.5
        val y = 2.5
        val length = Math.sqrt(x * x + y * y)
        assertEquals(Vector2(x, y).length(), length)
    }

    @Test
    fun test_normalize() {
        val x = 1.5
        val y = 2.5
        val length = Math.sqrt(x * x + y * y)
        val normalize = Vector2(x / length, y / length)
        assertEquals(Vector2(x, y).normalize(), normalize)
    }

    @Test
    fun test_dot() {
        val x1 = 1.5
        val y1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val dot = x1 * x2 + y1 * y2
        assertEquals(Vector2(x1, y1).dot(Vector2(x2, y2)), dot)
    }

    @Test
    fun test_cross() {
        val x1 = 1.5
        val y1 = 2.5
        val x2 = 3.5
        val y2 = 4.5
        val cross = x1 * y2 + y1 * x2
        assertEquals(Vector2(x1, y1).cross(Vector2(x2, y2)), cross)
    }

}