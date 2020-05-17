package com.gmail.jiangyang5157.android.router.fragment

import androidx.lifecycle.Lifecycle

@FragmentRouterDsl
class FragmentContainerLifecycleBuilderImpl {

    @FragmentRouterDsl
    var attachOn: Lifecycle.Event = Lifecycle.Event.ON_RESUME

    @FragmentRouterDsl
    var detachOn: Lifecycle.Event = Lifecycle.Event.ON_PAUSE

    internal fun build(): FragmentContainerLifecycle.Factory =
        FragmentContainerLifecycleImpl.Factory(
            attachEvent = attachOn,
            detachEvent = detachOn
        )
}
