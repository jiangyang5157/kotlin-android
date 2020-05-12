package com.gmail.jiangyang5157.kotlin.example.router.ui.uri.transition

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment1
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment3

class RouterFragment1And3Transition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is UriRouterFragment1 && exitFragment is UriRouterFragment3) {
            enterFragment.enterTransition = Slide(Gravity.BOTTOM)
            exitFragment.exitTransition = Slide(Gravity.TOP)
        }

        if (enterFragment is UriRouterFragment3 && exitFragment is UriRouterFragment1) {
            enterFragment.enterTransition = Slide(Gravity.TOP)
            exitFragment.exitTransition = Slide(Gravity.BOTTOM)
        }
    }
}
