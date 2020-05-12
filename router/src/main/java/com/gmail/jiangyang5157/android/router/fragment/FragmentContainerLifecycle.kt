package com.gmail.jiangyang5157.android.router.fragment

import androidx.lifecycle.Lifecycle

internal interface FragmentContainerLifecycle {

    interface Factory {
        operator fun invoke(router: FragmentRouter<*>): FragmentContainerLifecycle
    }

    fun setup(lifecycle: Lifecycle, container: FragmentContainer)
}
