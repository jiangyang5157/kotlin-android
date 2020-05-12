package com.gmail.jiangyang5157.android.router.fragment.transition

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
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

    @JvmName("registerGeneric")
    @FragmentRouterDsl
    inline fun <reified ExitFragment : Fragment, reified ExitRoute : Route,
        reified EnterFragment : Fragment, reified EnterRoute : Route> register(
        transition: GenericFragmentTransition<ExitFragment, ExitRoute, EnterFragment, EnterRoute>
    ) = register(transition.reified().erased())

    internal fun build(): FragmentTransition = transition
}
