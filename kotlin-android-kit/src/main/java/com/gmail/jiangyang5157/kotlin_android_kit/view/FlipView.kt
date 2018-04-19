package com.gmail.jiangyang5157.kotlin_android_kit.view

/**
 * Created by Yang Jiang on August 22, 2017
 */
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.Interpolator
import android.widget.FrameLayout

class FlipView : FrameLayout {

    interface FlipDuration {

        companion object {
            const val DEFAULT_DURATION: Long = 500
        }

        /**
         * @return Total duration for flip-in and flop-out animation
         */
        val duration: Long
    }

    interface FlipInterpolator {
        companion object {
            val DEFAULT_FLIP_IN: Interpolator = AccelerateInterpolator()
            val DEFAULT_FLIP_OUT: Interpolator = DecelerateInterpolator()
        }

        val flipIn: Interpolator
        val flipOut: Interpolator
    }

    interface FlipListener {
        /**
         * @param view The FlipView which has completed the flip.
         */
        fun onFlipped(view: FlipView)
    }

    private inner class AnimationListener : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            isFront = !isFront
            flipListener?.onFlipped(this@FlipView)
        }
    }

    enum class FlipDirection(
            internal val propertyName: String,
            internal val rotateFrom: Float,
            internal val rotateTo: Float) {
        LEFT_IN_LEFT_OUT("rotationY", -90f, -90f),
        LEFT_IN_RIGHT_OUT("rotationY", -90f, 90f),
        RIGHT_IN_RIGHT_OUT("rotationY", 90f, 90f),
        RIGHT_IN_LEFT_OUT("rotationY", 90f, -90f),
        BOTTOM_IN_BOTTOM_OUT("rotationX", -90f, -90f),
        BOTTOM_IN_TOP_OUT("rotationX", -90f, 90f),
        TOP_IN_TOP_OUT("rotationX", 90f, 90f),
        TOP_IN_BOTTOM_OUT("rotationX", 90f, -90f);

        internal val mRotateDefault = 0f

        internal fun init(front: View?, back: View?) {
            when (this) {
                LEFT_IN_LEFT_OUT,
                LEFT_IN_RIGHT_OUT,
                RIGHT_IN_RIGHT_OUT,
                RIGHT_IN_LEFT_OUT -> {
                    front?.rotationY = mRotateDefault
                    back?.rotationY = rotateFrom
                }
                BOTTOM_IN_BOTTOM_OUT,
                BOTTOM_IN_TOP_OUT,
                TOP_IN_TOP_OUT,
                TOP_IN_BOTTOM_OUT -> {
                    front?.rotationX = mRotateDefault
                    back?.rotationX = rotateFrom
                }
            }
        }
    }

    private var frontView: View? = null

    private var backView: View? = null

    private var flipDirection: FlipDirection? = null

    private var animatorSet: AnimatorSet? = null

    private val animationListener = AnimationListener()

    private var isFront = true

    var flipListener: FlipListener? = null

    var flipDuration: FlipDuration? = null

    var flipInterpolator: FlipInterpolator? = null

    private val CAMERA_DISTANCE_FACTOR = 8000

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * @param front The front view, null represents empty
     * *
     * @param back  The back view, null represents empty
     * *
     * @param dir   Direction for both flip-in and flip-out
     */
    fun init(front: View?, back: View?, dir: FlipDirection) {
        frontView = front
        backView = back
        flipDirection = dir

        removeAllViews()
        val distance = CAMERA_DISTANCE_FACTOR * resources.displayMetrics.density
        if (frontView != null) {
            frontView!!.cameraDistance = distance
            addView(frontView)
        }
        if (backView != null) {
            backView!!.cameraDistance = distance
            addView(backView)
        }

        flipDirection!!.init(frontView, backView)
    }

    /**
     * Try to start a flip animation, but do nothing if there is a flip animation running
     */
    fun apply() {
        flip(false)
    }

    /**
     * Immediately start a flip animation
     */
    fun commit() {
        flip(true)
    }

    private fun flip(force: Boolean): Boolean {
        if (animatorSet != null && animatorSet!!.isRunning) {
            if (force) {
                animatorSet!!.end()
            } else {
                return false
            }
        }
        animatorSet = createAnimatorSet()
        animatorSet!!.start()
        return true
    }

    private fun createAnimatorSet(): AnimatorSet {
        val flipOutAnimator: ObjectAnimator
        val flipInAnimator: ObjectAnimator
        if (isFront) {
            flipOutAnimator = ObjectAnimator.ofFloat(
                    frontView,
                    flipDirection!!.propertyName,
                    flipDirection!!.mRotateDefault,
                    flipDirection!!.rotateTo)
            flipInAnimator = ObjectAnimator.ofFloat(
                    backView,
                    flipDirection!!.propertyName,
                    flipDirection!!.rotateFrom,
                    flipDirection!!.mRotateDefault)
        } else {
            flipOutAnimator = ObjectAnimator.ofFloat(
                    backView,
                    flipDirection!!.propertyName,
                    flipDirection!!.mRotateDefault,
                    flipDirection!!.rotateTo)
            flipInAnimator = ObjectAnimator.ofFloat(
                    frontView,
                    flipDirection!!.propertyName,
                    flipDirection!!.rotateFrom,
                    flipDirection!!.mRotateDefault)
        }
        if (flipInterpolator == null) {
            flipOutAnimator.interpolator = FlipInterpolator.DEFAULT_FLIP_IN
            flipInAnimator.interpolator = FlipInterpolator.DEFAULT_FLIP_OUT
        } else {
            flipOutAnimator.interpolator = flipInterpolator!!.flipIn
            flipInAnimator.interpolator = flipInterpolator!!.flipOut
        }

        val ret = AnimatorSet()
        ret.playSequentially(flipOutAnimator, flipInAnimator)
        ret.addListener(animationListener)
        if (flipDuration == null) {
            ret.duration = FlipDuration.DEFAULT_DURATION
        } else {
            ret.duration = flipDuration!!.duration
        }

        return ret
    }
}