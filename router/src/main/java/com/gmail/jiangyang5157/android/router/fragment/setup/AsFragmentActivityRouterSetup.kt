package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.FragmentActivity

internal class AsFragmentActivityRouterSetup(activity: FragmentActivity) :
    FragmentRouterSetup,
    FragmentRouterHost by AsFragmentActivityRouterHost(activity),
    InvokeOnSaveInstanceState by InvokeOnActivitySaveInstanceState(
        activity
    )
