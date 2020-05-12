package com.gmail.jiangyang5157.android.router.fragment

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.core.requireMainThread
import java.lang.ref.WeakReference

typealias OnSaveInstanceStateCallback = (outState: Bundle) -> Unit

internal interface InvokeOnSaveInstanceStateSyntax {
    fun invokeOnSaveInstanceState(callback: OnSaveInstanceStateCallback)
}

internal class FragmentInvokeOnSaveInstanceStateSyntax(fragment: Fragment) :
    InvokeOnSaveInstanceStateSyntax {

    private val fragmentReference = WeakReference(fragment)

    private val onSaveInstanceStateCallbacks =
        mutableListOf<OnSaveInstanceStateCallback>()

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentDestroyed(fragmentManager: FragmentManager, fragment: Fragment) {
            if (fragment == fragmentReference.get() || fragmentReference.get() == null) {
                fragmentManager.unregisterFragmentLifecycleCallbacks(this)
            }
        }

        override fun onFragmentSaveInstanceState(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            outState: Bundle
        ) {
            if (fragment == fragmentReference.get()) {
                onSaveInstanceStateCallbacks.forEach { callback -> callback(outState) }
            }
        }
    }

    override fun invokeOnSaveInstanceState(callback: OnSaveInstanceStateCallback) {
        requireMainThread()
        onSaveInstanceStateCallbacks += callback
    }

    init {
        fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(
            fragmentLifecycleCallbacks,
            false
        )
    }
}

internal class ActivityInvokeOnSaveInstanceStateSyntax(activity: FragmentActivity) :
    InvokeOnSaveInstanceStateSyntax {

    private val activityReference = WeakReference(activity)

    private val application = activity.application

    private val onSaveInstanceStateCallbacks = mutableListOf<OnSaveInstanceStateCallback>()

    private val activityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityPaused(activity: Activity?) = Unit
        override fun onActivityResumed(activity: Activity?) = Unit
        override fun onActivityStarted(activity: Activity?) = Unit
        override fun onActivityStopped(activity: Activity?) = Unit
        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) = Unit

        override fun onActivityDestroyed(activity: Activity) {
            if (activity == activityReference.get() || activityReference.get() == null) {
                application.unregisterActivityLifecycleCallbacks(this)
            }
        }

        override fun onActivitySaveInstanceState(
            activity: Activity,
            outState: Bundle
        ) {
            if (activity == activityReference.get()) {
                onSaveInstanceStateCallbacks.forEach { callback ->
                    callback.invoke(outState)
                }
            }
        }
    }

    override fun invokeOnSaveInstanceState(callback: OnSaveInstanceStateCallback) {
        requireMainThread()
        onSaveInstanceStateCallbacks += callback
    }

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}
