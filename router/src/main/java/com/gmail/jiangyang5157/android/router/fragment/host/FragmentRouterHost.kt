package com.gmail.jiangyang5157.android.router.fragment.host

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

internal interface FragmentRouterHost {
    val lifecycle: Lifecycle
    val activity: FragmentActivity
}
