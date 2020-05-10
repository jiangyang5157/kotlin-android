package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.Fragment

class FragmentFragmentRouterSetupSyntax(fragment: Fragment) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by FragmentFragmentRouterHost(fragment),
    InvokeOnSaveInstanceStateSyntax by FragmentInvokeOnSaveInstanceStateSyntax(fragment)
