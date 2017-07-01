package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.support.test.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*
import java.io.Serializable

/**
 * Created by Yang Jiang on July 01, 2017
 */
class Object2StringUtilsTest {

    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.gmail.jiangyang5157.kotlin_android_core.test", appContext.packageName)
    }

    @Test
    fun object2String2Object() {
        val testClassOriginal = TestClass(1, "a")
        val testClassString = Object2StringUtils.object2String(testClassOriginal)
        println("testClassString: " + testClassString)
        val testClassResult = Object2StringUtils.string2Object(testClassString) as TestClass
        assertEquals(testClassOriginal, testClassResult)
        assertTrue(testClassOriginal == testClassResult)
        assertTrue(testClassOriginal !== testClassResult)
    }

}

class TestClass(var i: Int, var s: String) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as TestClass

        if (i != other.i) return false
        if (s != other.s) return false

        return true
    }

    override fun hashCode(): Int {
        var result = i
        result = 31 * result + s.hashCode()
        return result
    }

}