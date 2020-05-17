package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.fragment.host.AsFragmentActivity
import com.gmail.jiangyang5157.android.router.fragment.host.AsFragmentActivityRouterSetup
import com.gmail.jiangyang5157.android.router.fragment.host.expectThisToBeAFragmentActivity

interface RouterFragmentActivity :
    AsFragmentActivity,
    PopRetainRootImmediateOrFinish {

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?,
        @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragmentActivity().supportFragmentManager
    ) =
        AsFragmentActivityRouterSetup(
            expectThisToBeAFragmentActivity()
        ).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
}
