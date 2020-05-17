package com.gmail.jiangyang5157.android.router.fragment.host

import android.os.Bundle

typealias OnSaveStateCallback = (outState: Bundle) -> Unit

internal interface InvokeRouterOnSaveState {

    fun invokeOnSaveState(callback: OnSaveStateCallback)
}
