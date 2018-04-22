package com.gmail.jiangyang5157.kotlin_kit.render

/**
 * Created by Yang Jiang on July 16, 2017
 */
class FrameThread(fps: Int, callback: Callback) : Thread() {

    companion object {

        const val STATUS_RUNNING = 1 shl 0

        const val STATUS_PAUSED = 1 shl 1

        const val STATUS_FOCUSED = 1 shl 2

        const val STATUS_REFRESH = 1 shl 3
    }

    interface Callback {

        fun onFrame()

    }

    private val mFrameRate = FPSValidation(fps)

    private val mCallback = callback

    private val mLock = java.lang.Object()

    private var mStatus = 0

    fun getStatus() = mStatus

    private fun on(status: Int) {
        mStatus = mStatus or status
    }

    private fun off(status: Int) {
        mStatus = mStatus and status.inv()
    }

    fun checkStatus(status: Int): Boolean {
        return mStatus and status == status
    }

    override fun run() {
        while (true) {
            while (checkStatus(STATUS_RUNNING) && (checkStatus(STATUS_PAUSED) || !checkStatus(STATUS_FOCUSED))) {
                if (checkStatus(STATUS_REFRESH)) {
                    off(STATUS_REFRESH)
                    break
                }

                synchronized(mLock) {
                    mLock.wait()
                }
            }

            if (!checkStatus(STATUS_RUNNING)) {
                break
            }

            if (!mFrameRate.accept()) {
                continue
            }

            synchronized(mLock) {
                mCallback.onFrame()
            }
        }
    }

    fun onStart() {
        synchronized(mLock) {
            on(STATUS_RUNNING)
            start()
        }
    }

    fun onStop() {
        synchronized(mLock) {
            off(STATUS_RUNNING)
            mLock.notify()
        }

        var retry = true
        while (retry) {
            join()
            retry = false
        }
    }

    fun onPause() {
        synchronized(mLock) {
            on(STATUS_PAUSED)
        }
    }

    fun onResume() {
        synchronized(mLock) {
            off(STATUS_PAUSED)
            mLock.notify()
        }
    }

    fun onRefresh() {
        synchronized(mLock) {
            on(STATUS_REFRESH)
            mLock.notify()
        }
    }

    fun onFocused() {
        synchronized(mLock) {
            on(STATUS_FOCUSED)
            mLock.notify()
        }
    }

    fun onUnfocused() {
        synchronized(mLock) {
            off(STATUS_FOCUSED)
        }
    }

}