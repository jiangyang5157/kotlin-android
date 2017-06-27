package com.gmail.jiangyang5157.kotlin.base.render

import com.gmail.jiangyang5157.kotlin.base.utils.TimeUtils

// TODO refactor; tests;
class FrameRate(fps: Int) {

    private val npf: Long // nano per frame

    private var npfRealTime: Long = 0

    private var lastTime: Long = 0

    init {
        if (fps < 0) {
            throw IllegalArgumentException("Fps cannot less than 0.")
        }
        this.npf = if (fps == 0) 0 else TimeUtils.NANO_IN_SECOND / fps
    }

    val fpsRealTime: Long
        get() = Math.round(TimeUtils.NANO_IN_SECOND / npfRealTime.toDouble())

    fun newFrame(): Boolean {
        var ret = false

        val thisTime = System.nanoTime()
        val elapsedTime = thisTime - lastTime
        if (elapsedTime >= npf) {
            ret = true
            npfRealTime = elapsedTime
            lastTime = thisTime
        }

        return ret
    }

}
