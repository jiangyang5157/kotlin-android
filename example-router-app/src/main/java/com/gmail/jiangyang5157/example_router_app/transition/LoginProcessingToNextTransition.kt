package com.gmail.jiangyang5157.example_router_app.transition

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.GenericFragmentTransition
import com.gmail.jiangyang5157.example_router_app.ui.LoginFailedFragment
import com.gmail.jiangyang5157.example_router_app.ui.LoginProcessingFragment

class LoginProcessingToNextTransition :
    GenericFragmentTransition<LoginProcessingFragment, Route, Fragment, Route> {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: LoginProcessingFragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (enterFragment is LoginFailedFragment) {
            exitFragment.exitTransition = Slide(Gravity.TOP)
            enterFragment.enterTransition = Slide(Gravity.BOTTOM)
        } else {
            exitFragment.exitTransition = Slide(Gravity.START)
            enterFragment.enterTransition = Slide(Gravity.END)
        }
    }
}
