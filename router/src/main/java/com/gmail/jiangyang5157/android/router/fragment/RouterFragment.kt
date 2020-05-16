package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface RouterFragment :
    AsFragment,
    FragmentRouteUser {

    override val router: FragmentRouter<*>

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragment().childFragmentManager
    ) =
        FragmentFragmentRouterSetup(
            expectThisToBeAFragment()
        ).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
}
