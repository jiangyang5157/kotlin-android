package com.gmail.jiangyang5157.kotlin_core.render

/**
 * Created by Yang Jiang on July 16, 2017
 */
class RenderThread(fps: Int, listener: OnRenderListener) : Thread() {

    interface OnRenderListener {
        fun onRender()
    }

    private val frameRate = FrameRate(fps)
    private val renderingListener = listener

    private val lock = java.lang.Object()

    private var status = 0
    fun getStatus() = status

    private fun on(status: Int) {
        this.status = this.status or status
    }

    private fun off(status: Int) {
        this.status = this.status and status.inv()
    }

    fun check(status: Int): Boolean {
        return this.status and status == status
    }

    override fun run() {
        while (true) {
            while (check(STATUS_RUNNING) && (check(STATUS_PAUSED) || !check(STATUS_FOCUSED))) {
                if (check(STATUS_REFRESH)) {
                    off(STATUS_REFRESH)
                    break
                }

                synchronized(lock) {
                    lock.wait()
                }
            }

            if (!check(STATUS_RUNNING)) {
                break
            }

            if (!frameRate.newFrame()) {
                continue
            }

            synchronized(lock) {
                renderingListener.onRender()
            }
        }
    }

    fun onStart() {
        synchronized(lock) {
            on(STATUS_RUNNING)
            start()
        }
    }

    fun onStop() {
        synchronized(lock) {
            off(STATUS_RUNNING)
            lock.notify()
        }

        var retry = true
        while (retry) {
            join()
            retry = false
        }
    }

    fun onPause() {
        synchronized(lock) {
            on(STATUS_PAUSED)
        }
    }

    fun onResume() {
        synchronized(lock) {
            off(STATUS_PAUSED)
            lock.notify()
        }
    }

    fun onRefresh() {
        synchronized(lock) {
            on(STATUS_REFRESH)
            lock.notify()
        }
    }

    fun onFocused() {
        synchronized(lock) {
            on(STATUS_FOCUSED)
            lock.notify()
        }
    }

    fun onUnfocused() {
        synchronized(lock) {
            off(STATUS_FOCUSED)
        }
    }

    companion object {
        val STATUS_RUNNING = 1 shl 0
        val STATUS_PAUSED = 1 shl 1
        val STATUS_FOCUSED = 1 shl 2
        val STATUS_REFRESH = 1 shl 3
    }

}