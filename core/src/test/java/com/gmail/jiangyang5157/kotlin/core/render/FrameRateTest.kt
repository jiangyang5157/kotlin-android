package com.gmail.jiangyang5157.kotlin.core.render

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on June 28, 2017
 */
class FrameRateTest {

    @Test
    fun test_newFrame() {
        val fps = 1
        val frameRate = FrameRate(fps)
        assertTrue(frameRate.newFrame())
        assertFalse(frameRate.newFrame())
        Thread.sleep(1000)
        assertTrue(frameRate.newFrame())
        assertFalse(frameRate.newFrame())
        Thread.sleep(500)
        assertFalse(frameRate.newFrame())
        assertFalse(frameRate.newFrame())
        Thread.sleep(500)
        assertTrue(frameRate.newFrame())
        assertFalse(frameRate.newFrame())
        println(frameRate.fpsRealTime)
    }

    @Test
    fun test_newFrame_invalid_fps() {
        var frameRate = FrameRate(0)
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        println(frameRate.fpsRealTime)

        frameRate = FrameRate(-1)
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        assertTrue(frameRate.newFrame())
        println(frameRate.fpsRealTime)
    }

    @Test
    fun test_fpsRealTime() {
        val fps = 2
        val frameRate = FrameRate(fps)
        assertTrue(frameRate.newFrame())
        Thread.sleep(500)
        assertTrue(frameRate.newFrame())
        assertEquals(fps, frameRate.fpsRealTime)
        Thread.sleep(200)
        assertFalse(frameRate.newFrame())
        assertEquals(fps, frameRate.fpsRealTime)
        Thread.sleep(300)
        assertTrue(frameRate.newFrame())
        assertEquals(fps, frameRate.fpsRealTime)
    }

}