package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.FragmentActivity

internal class ActivityFragmentRouterSetupSyntax(activity: FragmentActivity) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by ActivityFragmentRouterHost(activity),
    InvokeOnSaveInstanceStateSyntax by ActivityInvokeOnSaveInstanceStateSyntax(activity)
