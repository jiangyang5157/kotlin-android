package com.gmail.jiangyang5157.android.router.fragment

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.gmail.jiangyang5157.android.router.core.requireMainThread
import com.gmail.jiangyang5157.android.router.fragment.setup.OnSaveInstanceStateCallback
import java.lang.ref.WeakReference

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

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            if (activity == activityReference.get()) {
                onSaveInstanceStateCallbacks.forEach { callback ->
                    callback.invoke(outState)
                }
            }
        }
    }

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    override fun invokeOnSaveInstanceState(callback: OnSaveInstanceStateCallback) {
        requireMainThread()
        onSaveInstanceStateCallbacks += callback
    }
}
