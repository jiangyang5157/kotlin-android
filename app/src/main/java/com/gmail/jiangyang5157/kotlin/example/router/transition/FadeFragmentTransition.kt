package com.gmail.jiangyang5157.kotlin.example.router.transition

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

/**
 * Example of transition, it will be used as default transition in the examples.
 *
 * A router can have multiple transitions registered.
 *
 * e.g.
 * ```
 * if (enterFragment is MyFragment1) {
 *     enterFragment.enterTransition = Slide(Gravity.END)
 *     exitFragment.exitTransition = Slide(Gravity.START)
 * }
 * // if ....
 * ```
 *
 */
class FadeFragmentTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        exitFragment.exitTransition = Fade(Fade.MODE_OUT)
        enterFragment.enterTransition = Fade(Fade.MODE_IN)
    }
}
