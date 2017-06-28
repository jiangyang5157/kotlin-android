package com.gmail.jiangyang5157.kotlin.core.render

import com.gmail.jiangyang5157.kotlin.core.utils.TimeUtils

/**
 * fps: frame per second
 */
class FrameRate(fps: Int) {

    // nano per frame
    private val npf: Long = if (fps <= 0) 0 else TimeUtils.NANO_IN_SECOND / fps

    private var lastTime: Long = 0

    private var npfRealTime: Long = 0

    val fpsRealTime: Int
        get() = Math.round(TimeUtils.NANO_IN_SECOND / npfRealTime.toDouble()).toInt()

    fun newFrame(): Boolean {
        var ret = false

        val thisTime = System.nanoTime()
        val elapsedTime = thisTime - lastTime
        if (elapsedTime >= npf) {
            lastTime = thisTime
            npfRealTime = elapsedTime
            ret = true
        }

        return ret
    }

}
