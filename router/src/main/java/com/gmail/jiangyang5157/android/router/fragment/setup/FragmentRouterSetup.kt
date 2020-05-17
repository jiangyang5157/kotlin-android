package com.gmail.jiangyang5157.android.router.fragment.setup

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.fragment.FragmentContainer
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter

internal interface FragmentRouterSetup :
    FragmentRouterHost,
    InvokeOnSaveInstanceState {

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        containerId: Int,
        fragmentManager: FragmentManager
    ) {
        invokeOnSaveInstanceState { outState -> saveState(outState) }
        restoreState(savedInstanceState)
        fragmentContainerLifecycle.setup(
            lifecycle,
            FragmentContainer(
                activity,
                fragmentManager,
                containerId
            )
        )
    }
}
