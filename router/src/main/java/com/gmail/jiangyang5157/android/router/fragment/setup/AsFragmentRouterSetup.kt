package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.Fragment

internal class AsFragmentRouterSetup(fragment: Fragment) :
    FragmentRouterSetup,
    FragmentRouterHost by AsFragmentRouterHost(fragment),
    InvokeOnSaveInstanceState by InvokeOnFragmentSaveInstanceState(
        fragment
    )

