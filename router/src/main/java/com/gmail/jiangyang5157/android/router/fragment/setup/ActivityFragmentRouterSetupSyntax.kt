package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.FragmentActivity

class ActivityFragmentRouterSetupSyntax(activity: FragmentActivity) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by ActivityFragmentRouterHost(activity),
    InvokeOnSaveInstanceStateSyntax by ActivityInvokeOnSaveInstanceStateSyntax(activity)
