package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface RouterFragmentActivity :
    AsFragmentActivity,
    PopRetainRootImmediateOrFinish {

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragmentActivity().supportFragmentManager
    ) =
        ActivityFragmentRouterSetup(
            expectThisToBeAFragmentActivity()
        ).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
}
