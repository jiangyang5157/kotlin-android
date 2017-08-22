package com.gmail.jiangyang5157.kotlin_math

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 26, 2017
 */
class Point3Test {

    @org.junit.Test
    fun test_constructor() {
        val empty = Point3()
        assertEquals(0.0, empty.x)
        assertEquals(0.0, empty.y)
        assertEquals(0.0, empty.z)

        val oneVal = Point3(1.1)
        assertEquals(1.1, oneVal.x)
        assertEquals(1.1, oneVal.y)
        assertEquals(1.1, oneVal.z)

        val threeVal = Point3(1.0, 2.0, 3.0)
        assertEquals(1.0, threeVal.x)
        assertEquals(2.0, threeVal.y)
        assertEquals(3.0, threeVal.z)
    }

    @org.junit.Test
    fun test_equality() {
        assertEquals(Point3(), Point3())
        assertNotEquals(Point3(), Point3(1.1))

        assertTrue(Point3() == Point3())
        assertTrue(Point3() !== Point3())
    }

}