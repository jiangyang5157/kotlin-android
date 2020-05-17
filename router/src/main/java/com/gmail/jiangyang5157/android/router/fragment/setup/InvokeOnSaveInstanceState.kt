package com.gmail.jiangyang5157.android.router.fragment.setup

import android.os.Bundle

typealias OnSaveInstanceStateCallback = (outState: Bundle) -> Unit

internal interface InvokeOnSaveInstanceState {

    fun invokeOnSaveInstanceState(callback: OnSaveInstanceStateCallback)
}
