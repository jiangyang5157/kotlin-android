package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.core.requireMainThread
import java.lang.ref.WeakReference

internal class InvokeOnFragmentSaveInstanceState(fragment: Fragment) :
    InvokeOnSaveInstanceState {

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
