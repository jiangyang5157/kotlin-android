package com.gmail.jiangyang5157.kotlin.ui.router

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

class RFTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (exitFragment is RouterFragment0) {
            exitFragment.exitTransition = Slide(Gravity.LEFT)
            enterFragment.enterTransition = Slide(Gravity.RIGHT)
        } else {
            exitFragment.exitTransition = Slide(Gravity.TOP)
            enterFragment.enterTransition = Slide(Gravity.BOTTOM)
        }
    }
}

