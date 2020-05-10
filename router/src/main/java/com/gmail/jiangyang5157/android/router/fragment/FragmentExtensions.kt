package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.error.RouterException

interface FragmentExtensions

internal fun FragmentExtensions.expectThisToBeAFragment() =
    this as? Fragment ?: throw RouterException(
        "${RouterFragment::class.java.simpleName} only works for androidx.fragment.app.Fragment"
    )
