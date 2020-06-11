package com.gmail.jiangyang5157.example_router_app.transition

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Slide
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.example_router_app.ui.ChatFragment
import com.gmail.jiangyang5157.example_router_app.ui.ContactListFragment

class ContactListToChatTransition : FragmentTransition {

    override fun setup(
        transaction: FragmentTransaction,
        exitFragment: Fragment, exitRoute: Route,
        enterFragment: Fragment, enterRoute: Route
    ) {
        if (exitFragment is ContactListFragment && enterFragment is ChatFragment) {
            exitFragment.exitTransition = Slide(Gravity.START)
            enterFragment.enterTransition = Slide(Gravity.END)
        } else if (exitFragment is ChatFragment && enterFragment is ContactListFragment) {
            exitFragment.exitTransition = Slide(Gravity.END)
            enterFragment.enterTransition = Slide(Gravity.START)
        }
    }
}

