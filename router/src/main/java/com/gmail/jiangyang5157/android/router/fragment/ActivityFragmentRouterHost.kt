package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

internal class ActivityFragmentRouterHost(override val activity: FragmentActivity) :
    FragmentRouterHost {

    override val lifecycle: Lifecycle get() = activity.lifecycle
}


