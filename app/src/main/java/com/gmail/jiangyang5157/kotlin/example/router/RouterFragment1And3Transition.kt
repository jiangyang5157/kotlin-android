package com.gmail.jiangyang5157.kotlin.example.router

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

class RouterFragment1And3Transition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is RouterFragment1 && exitFragment is RouterFragment3) {
            enterFragment.enterTransition = Slide(Gravity.BOTTOM)
            exitFragment.exitTransition = Slide(Gravity.TOP)
        }

        if (enterFragment is RouterFragment3 && exitFragment is RouterFragment1) {
            enterFragment.enterTransition = Slide(Gravity.TOP)
            exitFragment.exitTransition = Slide(Gravity.BOTTOM)
        }
    }
}
