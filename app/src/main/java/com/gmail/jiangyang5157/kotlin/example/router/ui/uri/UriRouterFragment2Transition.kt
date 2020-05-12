package com.gmail.jiangyang5157.kotlin.example.router.ui.uri

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.kotlin.example.router.ui.uri.UriRouterFragment2

class UriRouterFragment2Transition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is UriRouterFragment2) {
            enterFragment.enterTransition = Slide(Gravity.BOTTOM)
            exitFragment.exitTransition = Slide(Gravity.TOP)
        }
    }
}
