package com.gmail.jiangyang5157.kotlin.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yang Jiang on June 26, 2017
 */
class Point2Test {

    @Test
    fun test_constructor() {
        val point = Point2(1.0, 2.0)
        assertEquals(1.0, point.x)
        assertEquals(2.0, point.y)

        val pointZero = Point2()
        assertEquals(0.0, pointZero.x)
        assertEquals(0.0, pointZero.y)

        val pointSingle = Point2(1.1)
        assertEquals(1.1, pointSingle.x)
        assertEquals(1.1, pointSingle.y)
    }

    @Test
    fun test_overrider_operator() {
        val a = Point2(10.0, 10.0)
        val b = Point2(-2.0, 2.0)

        assertEquals(Point2(2.0, -2.0), -b)

        assertEquals(Point2(8.0, 12.0), a + b)
        assertEquals(Point2(30.0, 30.0), a + 20)
        assertEquals(Point2(30.2, 30.2), a + 20.2)

        assertEquals(Point2(12.0, 8.0), a - b)
        assertEquals(Point2(-10.0, -10.0), a - 20)
        assertEquals(Point2(-10.2, -10.2), a - 20.2)

        assertEquals(Point2(-20.0, 20.0), a * b)
        assertEquals(Point2(200.0, 200.0), a * 20)
        assertEquals(Point2(202.0, 202.0), a * 20.2)

        assertEquals(Point2(-5.0, 5.0), a / b)
        assertEquals(Point2(0.5, 0.5), a / 20)
        assertEquals(Point2(1.0, 1.0), a / 10)
    }

}