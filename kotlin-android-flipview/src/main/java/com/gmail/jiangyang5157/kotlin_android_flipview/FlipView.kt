package com.gmail.jiangyang5157.kotlin_android_flipview

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

    interface ConfigDuration {

        /**
         * @return Total duration for flip-in and flop-out animation
         */
        fun provideFlipDuration(): Long

        companion object {
            val DEFAULT_DURATION: Long = 500
        }
    }

    interface ConfigInterpolator {
        fun provideFlipInInterpolator(): Interpolator

        fun provideFlipOutInterpolator(): Interpolator
    }

    interface Listener {
        /**
         * @param view The FlipView which has completed the flip.
         */
        fun onFlipped(view: FlipView)
    }

    enum class Direction(
            internal val mPropertyName: String,
            internal val mRotateFrom: Float,
            internal val mRotateTo: Float) {
        LEFT_IN_LEFT_OUT("rotationY", -90f, -90f),
        LEFT_IN_RIGHT_OUT("rotationY", -90f, 90f),
        RIGHT_IN_RIGHT_OUT("rotationY", 90f, 90f),
        RIGHT_IN_LEFT_OUT("rotationY", 90f, -90f),
        BOTTOM_IN_BOTTOM_OUT("rotationX", -90f, -90f),
        BOTTOM_IN_TOP_OUT("rotationX", -90f, 90f),
        TOP_IN_TOP_OUT("rotationX", 90f, 90f),
        TOP_IN_BOTTOM_OUT("rotationX", 90f, -90f);

        internal val mRotateDefault = 0f

        internal fun prepare(front: View?, back: View?) {
            when (this) {
                LEFT_IN_LEFT_OUT,
                LEFT_IN_RIGHT_OUT,
                RIGHT_IN_RIGHT_OUT,
                RIGHT_IN_LEFT_OUT -> {
                    front?.rotationY = mRotateDefault
                    back?.rotationY = mRotateFrom
                }
                BOTTOM_IN_BOTTOM_OUT,
                BOTTOM_IN_TOP_OUT,
                TOP_IN_TOP_OUT,
                TOP_IN_BOTTOM_OUT -> {
                    front?.rotationX = mRotateDefault
                    back?.rotationX = mRotateFrom
                }
            }
        }
    }

    private inner class AnimationListener : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            isFront = !isFront
            listener?.onFlipped(this@FlipView)
        }
    }

    private var frontView: View? = null

    private var backView: View? = null

    private var isFront = true

    private var mDirection: Direction? = null

    private var mAnimatorSet: AnimatorSet? = null

    private val mAnimationListener = AnimationListener()

    var configDuration: ConfigDuration? = null

    var configInterpolator: ConfigInterpolator? = null

    var listener: Listener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * @param front The front view, null represents empty
     * *
     * @param back  The back view, null represents empty
     * *
     * @param dir   Direction for both flip-in and flip-out
     */
    fun reset(front: View?, back: View?, dir: Direction) {
        frontView = front
        backView = back
        mDirection = dir

        removeAllViews()
        if (frontView != null) addView(frontView)
        if (backView != null) addView(backView)

        mDirection!!.prepare(frontView, backView)
    }

    /**
     * Try to start a flip animation, but do nothing if there is a flip animation running
     */
    fun applyFlip() {
        flip(false)
    }

    /**
     * Immediately start a flip animation
     */
    fun commitFlip() {
        flip(true)
    }

    private fun flip(force: Boolean): Boolean {
        if (mAnimatorSet != null && mAnimatorSet!!.isRunning) {
            if (force) {
                mAnimatorSet!!.end()
            } else {
                return false
            }
        }
        mAnimatorSet = createAnimatorSet()
        mAnimatorSet!!.start()
        return true
    }

    private fun createAnimatorSet(): AnimatorSet {
        val flipOutAnimator: ObjectAnimator
        val flipInAnimator: ObjectAnimator
        if (isFront) {
            flipOutAnimator = ObjectAnimator.ofFloat(
                    frontView,
                    mDirection!!.mPropertyName,
                    mDirection!!.mRotateDefault,
                    mDirection!!.mRotateTo)
            flipInAnimator = ObjectAnimator.ofFloat(
                    backView,
                    mDirection!!.mPropertyName,
                    mDirection!!.mRotateFrom,
                    mDirection!!.mRotateDefault)
        } else {
            flipOutAnimator = ObjectAnimator.ofFloat(
                    backView,
                    mDirection!!.mPropertyName,
                    mDirection!!.mRotateDefault,
                    mDirection!!.mRotateTo)
            flipInAnimator = ObjectAnimator.ofFloat(
                    frontView,
                    mDirection!!.mPropertyName,
                    mDirection!!.mRotateFrom,
                    mDirection!!.mRotateDefault)
        }
        if (configInterpolator == null) {
            flipOutAnimator.interpolator = AccelerateInterpolator()
            flipInAnimator.interpolator = DecelerateInterpolator()
        } else {
            flipOutAnimator.interpolator = configInterpolator!!.provideFlipOutInterpolator()
            flipInAnimator.interpolator = configInterpolator!!.provideFlipInInterpolator()
        }

        val ret = AnimatorSet()
        ret.playSequentially(flipOutAnimator, flipInAnimator)
        ret.addListener(mAnimationListener)
        if (configDuration == null) {
            ret.duration = ConfigDuration.DEFAULT_DURATION
        } else {
            ret.duration = configDuration!!.provideFlipDuration()
        }

        return ret
    }
}