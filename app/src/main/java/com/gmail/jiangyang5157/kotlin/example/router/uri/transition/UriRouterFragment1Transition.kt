package com.gmail.jiangyang5157.kotlin.example.router.uri.transition

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.uri.UriRouterFragment1

class UriRouterFragment1Transition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is UriRouterFragment1) {
            enterFragment.enterTransition = Slide(Gravity.RIGHT)
            exitFragment.exitTransition = Slide(Gravity.LEFT)
        }
    }
}
