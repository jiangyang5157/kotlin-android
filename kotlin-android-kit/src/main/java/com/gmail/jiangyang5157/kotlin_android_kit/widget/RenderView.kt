package com.gmail.jiangyang5157.kotlin_android_kit.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.gmail.jiangyang5157.kotlin_android_kit.render.CanvasRenderable
import com.gmail.jiangyang5157.kotlin_kit.render.RenderThread
import com.gmail.jiangyang5157.kotlin_kit.render.Renderable

/**
 * Created by Yang Jiang on July 18, 2017
 */
open class RenderView : SurfaceView, SurfaceHolder.Callback, Renderable {

    companion object {

        const val TAG = "RenderView"

        const val FPS = 60
    }

    private var mRenderThread: RenderThread? = null

    private var mCanvasRenderable: CanvasRenderable? = null

    constructor(context: Context)
            : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        Log.d(TAG, "surfaceChanged $width x $height")
        mRenderThread?.onRefresh()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        Log.d(TAG, "surfaceDestroyed")
        mRenderThread?.onStop()
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        Log.d(TAG, "surfaceCreated")
        if (mRenderThread == null || mRenderThread?.state === Thread.State.TERMINATED) {
            mRenderThread = RenderThread(FPS, this)
        }
        mRenderThread?.onStart()
        mRenderThread?.onPause()
    }

    fun resume() {
        mRenderThread?.onResume()
    }

    fun pauseRender() {
        mRenderThread?.onPause()
    }

    fun refreshRender() {
        mRenderThread?.onPause()
    }

    final override fun onRender() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas(null)
            mCanvasRenderable?.onRender(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun setCanvasRenderable(onRenderListener: CanvasRenderable) {
        this.mCanvasRenderable = onRenderListener
    }

    fun getCanvasRenderable(): CanvasRenderable? = mCanvasRenderable

}