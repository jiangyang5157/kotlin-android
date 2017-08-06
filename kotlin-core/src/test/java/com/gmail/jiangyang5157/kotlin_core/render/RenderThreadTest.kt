package com.gmail.jiangyang5157.kotlin_core.render

import com.gmail.jiangyang5157.kotlin_core.render.RenderThread.OnRenderListener
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Created by Yang Jiang on August 06, 2017
 */
class RenderThreadTest {

    @Test
    fun test_status() {
        val thread = RenderThread(5000, object : OnRenderListener {
            override fun onRender() {
                println("OnRenderListener.onRender()")
            }
        })

        assertNotEquals(RenderThread.STATUS_RUNNING, thread.getStatus())
        assertNotEquals(RenderThread.STATUS_PAUSED, thread.getStatus())
        assertNotEquals(RenderThread.STATUS_FOCUSED, thread.getStatus())
        assertNotEquals(RenderThread.STATUS_REFRESH, thread.getStatus())

        assertFalse(thread.check(RenderThread.STATUS_RUNNING))
        assertFalse(thread.check(RenderThread.STATUS_PAUSED))
        assertFalse(thread.check(RenderThread.STATUS_FOCUSED))
        assertFalse(thread.check(RenderThread.STATUS_REFRESH))

        thread.onStart()
        assertTrue(thread.check(RenderThread.STATUS_RUNNING))

        thread.onPause()
        thread.onPause()
        thread.onPause()
        thread.onPause()
        thread.onPause()
        assertTrue(thread.check(RenderThread.STATUS_PAUSED))

        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        assertTrue(thread.check(RenderThread.STATUS_FOCUSED))

        thread.onResume()
        thread.onResume()
        thread.onResume()
        thread.onResume()
        thread.onResume()
        assertFalse(thread.check(RenderThread.STATUS_PAUSED))

        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        thread.onUnfocused()
        assertFalse(thread.check(RenderThread.STATUS_FOCUSED))

        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        thread.onFocused()
        assertTrue(thread.check(RenderThread.STATUS_FOCUSED))

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
        assertFalse(thread.check(RenderThread.STATUS_RUNNING))
    }

}