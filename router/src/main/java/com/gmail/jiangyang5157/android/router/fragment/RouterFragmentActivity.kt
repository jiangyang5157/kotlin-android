package com.gmail.jiangyang5157.android.router.fragment

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentActivityExtension
import com.gmail.jiangyang5157.android.router.fragment.setup.PopRetainRootImmediateOrFinishSyntax
import com.gmail.jiangyang5157.android.router.fragment.setup.PopRetainRootImmediateSyntax
import com.gmail.jiangyang5157.android.router.fragment.setup.expectThisToBeAFragmentActivity

interface RouterFragmentActivity :
    FragmentActivityExtension,
    PopRetainRootImmediateSyntax,
    PopRetainRootImmediateOrFinishSyntax {

    fun FragmentRouter<*>.setup(
        savedInstanceState: Bundle?, @IdRes containerId: Int,
        fragmentManager: FragmentManager = expectThisToBeAFragmentActivity().supportFragmentManager
    ) {
        ActivityFragmentRouterSetupSyntax(
            expectThisToBeAFragmentActivity()
        ).run {
            setup(savedInstanceState, containerId, fragmentManager)
        }
    }
}
