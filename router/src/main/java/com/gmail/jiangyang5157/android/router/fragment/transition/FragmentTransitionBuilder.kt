package com.gmail.jiangyang5157.android.router.fragment.transition

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterDsl

@FragmentRouterDsl
class FragmentTransitionBuilder {

    private var transition: FragmentTransition = EmptyFragmentTransition

    @FragmentRouterDsl
    fun register(transition: FragmentTransition) {
        this.transition += transition
    }

    @FragmentRouterDsl
    fun clear() {
        this.transition = EmptyFragmentTransition
    }

    fun build(): FragmentTransition = transition
}
