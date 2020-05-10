package com.gmail.jiangyang5157.android.router.fragment.transition

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gmail.jiangyang5157.android.router.core.Route

/**
 * Generic version of [FragmentTransition].
 * Allows for constraining the transition by the generic type parameters.
 *
 * @see FragmentTransition
 */
interface GenericFragmentTransition<
    in ExitFragment : Fragment, in ExitRoute : Route,
    in EnterFragment : Fragment, in EnterRoute : Route> {
    fun setup(
        transaction: FragmentTransaction,
        exitFragment: ExitFragment, exitRoute: ExitRoute,
        enterFragment: EnterFragment, enterRoute: EnterRoute
    )
}


