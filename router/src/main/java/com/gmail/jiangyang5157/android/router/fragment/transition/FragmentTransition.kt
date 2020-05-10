package com.gmail.jiangyang5157.android.router.fragment.transition

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.FragmentRoute

/**
 * # FragmentTransition
 * Represents a way of hooking into the [FragmentRoute] to setup transitions before the routing of a fragment is executed.
 *
 * ## Usage
 * - Simple Slide (Going from a `HomeRoute` to a `SettingsRoute`)
 * ```
 * class HomeToSettingsTransition: FragmentTransition {
 *
 *     override fun setup(
 *         transaction: FragmentTransaction,
 *         exitFragment: Fragment, exitRoute: Route,
 *         enterFragment: Fragment, enterRoute: Route
 *     ) {
 *         if(exitFragment is HomeFragment && enterFragment is SettingsFragment) {
 *            exitFragment.exitTransition = Slide(Gravity.LEFT)
 *            enterFragment.enterTransition = Slide(Gravity.RIGHT)
 *         }
 *         if(exitFragment is SettingsFragment && enterFragment is HomeFragment) {
 *            exitFragment.exitTransition = Slide(Gravity.LEFT)
 *            enterFragment.enterTransition = Slide(Gravity.RIGHT)
 *         }
 *     }
 * }
 * ```
 * - Transitions can be combined/chained to build a new transition which invokes all setup methods
 * ```
 * val loginToRegisterTransition: FragmentTransition = ...
 * val loginToHomeTransition: FragmentTransition = ...
 * val homeToSettingsTransition: FragmentTransition = ...
 * val transitionSet: FragmentTransition = loginToRegisterTransition + loginToHomeTransition + homeToSettingsTransition
 * ```
 *
 * ## Note
 * - The setup method will be called before the the router commits any fragment transaction
 * - The setup method will be called for pushing a new route to the top
 * - The setup method will be called for pop the current top route
 * - The setup method won't be called for changes to the routing stack that don't affect the top route
 * - The transaction parameter should not be used for anything different than setting up transitions
 *
 * @see GenericFragmentTransition
 */
typealias FragmentTransition = GenericFragmentTransition<Fragment, Route, Fragment, Route>

operator fun FragmentTransition.plus(other: FragmentTransition): FragmentTransition {
    return CompositeFragmentTransition(this, other)
}

private class CompositeFragmentTransition(
    private val first: FragmentTransition,
    private val second: FragmentTransition
) : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route, enterFragment: Fragment, enterRoute: Route
    ) {
        first.setup(transaction, exitFragment, exitRoute, enterFragment, enterRoute)
        second.setup(transaction, exitFragment, exitRoute, enterFragment, enterRoute)
    }
}