package com.gmail.jiangyang5157.kotlin_kit.render

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on August 06, 2017
 */
class FrameThreadTest {

    @Test
    fun test_status() {
        val thread = FrameThread(5000, object : FrameThread.Callback {
            override fun onFrame() {
                println("FrameThread.Callback.onFrame()")
            }
        })

        assertNotEquals(FrameThread.STATUS_RUNNING, thread.getStatus())
        assertNotEquals(FrameThread.STATUS_PAUSED, thread.getStatus())
        assertNotEquals(FrameThread.STATUS_FOCUSED, thread.getStatus())
        assertNotEquals(FrameThread.STATUS_REFRESH, thread.getStatus())

        assertFalse(thread.checkStatus(FrameThread.STATUS_RUNNING))
        assertFalse(thread.checkStatus(FrameThread.STATUS_PAUSED))
        assertFalse(thread.checkStatus(FrameThread.STATUS_FOCUSED))
        assertFalse(thread.checkStatus(FrameThread.STATUS_REFRESH))

        thread.onStart()
        assertTrue(thread.checkStatus(FrameThread.STATUS_RUNNING))

        thread.onPause()
        thread.onPause()
        thread.onPause()
        thread.onPause()
        thread.onPause()
        assertTrue(thread.checkStatus(FrameThread.STATUS_PAUSED))

        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        assertTrue(thread.checkStatus(FrameThread.STATUS_FOCUSED))

        thread.onResume()
        thread.onResume()
        thread.onResume()
        thread.onResume()
        thread.onResume()
        assertFalse(thread.checkStatus(FrameThread.STATUS_PAUSED))

        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        assertFalse(thread.checkStatus(FrameThread.STATUS_FOCUSED))

        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        assertTrue(thread.checkStatus(FrameThread.STATUS_FOCUSED))

        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()
        thread.onRefresh()

        thread.onStop()
        assertFalse(thread.checkStatus(FrameThread.STATUS_RUNNING))
    }

}