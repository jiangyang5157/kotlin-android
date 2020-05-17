package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

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

class FragmentFragmentRouterSetup(fragment: Fragment) :
    FragmentRouterSetup,
    FragmentRouterHost by FragmentFragmentRouterHost(
        fragment
    ),
    InvokeOnSaveInstanceState by InvokeOnFragmentSaveInstanceState(
        fragment
    )


class ActivityFragmentRouterSetup(activity: FragmentActivity) :
    FragmentRouterSetup,
    FragmentRouterHost by ActivityFragmentRouterHost(
        activity
    ),
    InvokeOnSaveInstanceState by InvokeOnActivitySaveInstanceState(
        activity
    )
