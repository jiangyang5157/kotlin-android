package com.gmail.jiangyang5157.kotlin.math

import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yang Jiang on June 26, 2017
 */
class Point3Test {

    @Test
    fun test_constructor() {
        val point = Point3(1.0, 2.0, 3.0)
        assertEquals(1.0, point.x)
        assertEquals(2.0, point.y)
        assertEquals(3.0, point.z)

        val pointZero = Point3()
        assertEquals(0.0, pointZero.x)
        assertEquals(0.0, pointZero.y)
        assertEquals(0.0, pointZero.z)

        val pointSingle = Point3(1.1)
        assertEquals(1.1, pointSingle.x)
        assertEquals(1.1, pointSingle.y)
        assertEquals(1.1, pointSingle.z)

        val pointPoint = Point3(Point3(2.2, 2.2, 2.2))
        assertEquals(2.2, pointPoint.x)
        assertEquals(2.2, pointPoint.y)
        assertEquals(2.2, pointPoint.z)
    }

    @Test
    fun test_overrider_operator() {
        val a = Point3(10.0, 10.0, 10.0)
        val b = Point3(-2.0, 2.0, 1.0)

        assertEquals(Point3(2.0, -2.0, -1.0), -b)

        assertEquals(Point3(8.0, 12.0, 11.0), a + b)
        assertEquals(Point3(30.0, 30.0, 30.0), a + 20)
        assertEquals(Point3(30.2, 30.2, 30.2), a + 20.2)

        assertEquals(Point3(12.0, 8.0, 9.0), a - b)
        assertEquals(Point3(-10.0, -10.0, -10.0), a - 20)
        assertEquals(Point3(-10.2, -10.2, -10.2), a - 20.2)

        assertEquals(Point3(-20.0, 20.0, 10.0), a * b)
        assertEquals(Point3(200.0, 200.0, 200.0), a * 20)
        assertEquals(Point3(202.0, 202.0, 202.0), a * 20.2)

        assertEquals(Point3(-5.0, 5.0, 10.0), a / b)
        assertEquals(Point3(0.5, 0.5, 0.5), a / 20)
        assertEquals(Point3(1.0, 1.0, 1.0), a / 10)
    }

}