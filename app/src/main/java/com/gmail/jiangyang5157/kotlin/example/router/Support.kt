package com.gmail.jiangyang5157.kotlin.example.router

import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.fragment.*
import com.gmail.jiangyang5157.android.router.fragment.host.RouterFragment
import com.gmail.jiangyang5157.android.router.fragment.host.RouterFragmentActivity
import com.gmail.jiangyang5157.android.router.fragment.host.expectThisToBeAFragment

interface RouterFragmentActivitySupport<T : Route> :
    RouterFragmentActivity {
    val router: FragmentRouter<T>
}

interface RouterFragmentSupport<T : Route> :
    RouterFragment {

    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<T>
        get() = (expectThisToBeAFragment().activity as RouterFragmentActivitySupport<T>).router
}
