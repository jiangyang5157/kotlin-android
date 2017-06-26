package com.gmail.jiangyang5157.kotlin.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yang Jiang on June 26, 2017
 */
class PointTest {

    @Test
    fun test_constructor() {
        val point = Point(1.0, 2.0)
        assertEquals(1.0, point.x)
        assertEquals(2.0, point.y)

        val pointDefault = Point()
        assertEquals(0.0, pointDefault.x)
        assertEquals(0.0, pointDefault.y)
    }

    @Test
    fun test_overrider_operator() {
        val a = Point(10.0, 10.0)
        val b = Point(-2.0, 2.0)

        assertEquals(Point(2.0, -2.0), -b)

        assertEquals(Point(8.0, 12.0), a + b)
        assertEquals(Point(30.0, 30.0), a + 20)
        assertEquals(Point(30.2, 30.2), a + 20.2)

        assertEquals(Point(12.0, 8.0), a - b)
        assertEquals(Point(-10.0, -10.0), a - 20)
        assertEquals(Point(-10.2, -10.2), a - 20.2)

        assertEquals(Point(-20.0, 20.0), a * b)
        assertEquals(Point(200.0, 200.0), a * 20)
        assertEquals(Point(202.0, 202.0), a * 20.2)

        assertEquals(Point(-5.0, 5.0), a / b)
        assertEquals(Point(0.5, 0.5), a / 20)
        assertEquals(Point(1.0, 1.0), a / 10)
    }

}