package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.fragment.setup.*
import com.gmail.jiangyang5157.android.router.fragment.setup.ActivityFragmentRouterHost
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentFragmentRouterHost
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentRouterHost
import com.gmail.jiangyang5157.android.router.fragment.setup.InvokeOnSaveInstanceStateSyntax

internal interface FragmentRouterSetupSyntax :
    FragmentRouterHost,
    InvokeOnSaveInstanceStateSyntax {

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

class FragmentFragmentRouterSetupSyntax(fragment: Fragment) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by FragmentFragmentRouterHost(fragment),
    InvokeOnSaveInstanceStateSyntax by FragmentInvokeOnSaveInstanceStateSyntax(fragment)


class ActivityFragmentRouterSetupSyntax(activity: FragmentActivity) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by ActivityFragmentRouterHost(activity),
    InvokeOnSaveInstanceStateSyntax by ActivityInvokeOnSaveInstanceStateSyntax(activity)
