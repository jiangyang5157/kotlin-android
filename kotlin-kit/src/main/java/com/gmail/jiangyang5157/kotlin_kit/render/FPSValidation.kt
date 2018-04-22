package com.gmail.jiangyang5157.kotlin_kit.render

import com.gmail.jiangyang5157.kotlin_kit.utils.TimeUtils

/**
 * Created by Yang Jiang on June 27, 2017
 */
class FPSValidation(fps: Int) {

    // nano per frame
    private val npf: Long = if (fps <= 0) 0 else TimeUtils.NANO_IN_SECOND / fps

    private var lastTime: Long = 0

    private var npfRealTime: Long = 0

    val fpsRealTime: Int
        get() = Math.round(TimeUtils.NANO_IN_SECOND / npfRealTime.toDouble()).toInt()

    fun accept(): Boolean {
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
