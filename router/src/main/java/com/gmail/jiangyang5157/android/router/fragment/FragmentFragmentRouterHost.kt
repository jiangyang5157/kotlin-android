package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

internal class FragmentFragmentRouterHost(private val fragment: Fragment) :
    FragmentRouterHost {
    override val lifecycle: Lifecycle get() = fragment.lifecycle
    override val activity: FragmentActivity get() = fragment.requireActivity()
}
