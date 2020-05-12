package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentExtensions
import com.gmail.jiangyang5157.android.router.fragment.setup.expectThisToBeAFragment

interface RouterFragment :
    FragmentExtensions,
    FragmentGetRouteSyntax {

    override val router: FragmentRouter<*>

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragment().childFragmentManager
    ) {
        FragmentFragmentRouterSetupSyntax(
            expectThisToBeAFragment()
        ).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
    }
}
