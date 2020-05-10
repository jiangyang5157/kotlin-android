package com.gmail.jiangyang5157.android.router.fragment

import com.gmail.jiangyang5157.android.router.core.RoutingStack
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

interface FragmentStackPatcher {
    operator fun invoke(
        transition: FragmentTransition,
        container: FragmentContainer,
        oldStack: RoutingStack<*>,
        newStack: FragmentRoutingStack<*>
    )
}
