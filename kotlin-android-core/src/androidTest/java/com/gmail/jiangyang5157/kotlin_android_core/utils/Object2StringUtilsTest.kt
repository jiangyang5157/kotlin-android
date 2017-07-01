package com.gmail.jiangyang5157.kotlin_android_core.utils

import android.support.test.InstrumentationRegistry
import org.junit.Test

import org.junit.Assert.*

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
    fun object2String() {
    }

    @Test
    fun string2Object() {
    }

    @Test
    fun bitmap2String() {
    }

    @Test
    fun string2Drawable() {
    }

}