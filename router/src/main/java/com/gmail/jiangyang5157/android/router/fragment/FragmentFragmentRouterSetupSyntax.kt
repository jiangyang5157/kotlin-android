package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentFragmentRouterHost
import com.gmail.jiangyang5157.android.router.fragment.setup.FragmentRouterHost
import com.gmail.jiangyang5157.android.router.fragment.setup.InvokeOnSaveInstanceStateSyntax

class FragmentFragmentRouterSetupSyntax(fragment: Fragment) :
    FragmentRouterSetupSyntax,
    FragmentRouterHost by FragmentFragmentRouterHost(fragment),
    InvokeOnSaveInstanceStateSyntax by FragmentInvokeOnSaveInstanceStateSyntax(fragment)
