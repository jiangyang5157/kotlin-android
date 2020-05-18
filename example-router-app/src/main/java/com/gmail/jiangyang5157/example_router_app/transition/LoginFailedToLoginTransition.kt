package com.gmail.jiangyang5157.example_router_app.transition

import android.view.Gravity
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.GenericFragmentTransition
import com.gmail.jiangyang5157.example_router_app.fragment.LoginFailedFragment
import com.gmail.jiangyang5157.example_router_app.fragment.LoginFragment

class LoginFailedToLoginTransition :
    GenericFragmentTransition<LoginFailedFragment, Route, LoginFragment, Route> {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: LoginFailedFragment, exitRoute: Route,
        enterFragment: LoginFragment, enterRoute: Route
    ) {
        exitFragment.exitTransition = Slide(Gravity.END)
        enterFragment.enterTransition = Slide(Gravity.START)
    }
}
