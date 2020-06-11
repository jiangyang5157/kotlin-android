package com.gmail.jiangyang5157.example_router_app.transition

import android.view.Gravity
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.GenericFragmentTransition
import com.gmail.jiangyang5157.example_router_app.ui.LoginFragment
import com.gmail.jiangyang5157.example_router_app.ui.LoginProcessingFragment

class LoginToLoginProcessingTransition :
    GenericFragmentTransition<LoginFragment, Route, LoginProcessingFragment, Route> {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: LoginFragment, exitRoute: Route,
        enterFragment: LoginProcessingFragment, enterRoute: Route
    ) {
        exitFragment.exitTransition = Slide(Gravity.BOTTOM)
        enterFragment.enterTransition = Slide(Gravity.TOP)
    }
}



