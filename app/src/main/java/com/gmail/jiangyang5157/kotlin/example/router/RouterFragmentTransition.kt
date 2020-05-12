package com.gmail.jiangyang5157.kotlin.example.router

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition

class RouterFragmentTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is RouterFragment0) {
            enterFragment.enterTransition = Slide(Gravity.LEFT)
            exitFragment.exitTransition = Slide(Gravity.RIGHT)
        } else {
            enterFragment.enterTransition = Slide(Gravity.RIGHT)
            exitFragment.exitTransition = Slide(Gravity.LEFT)
        }
    }
}
