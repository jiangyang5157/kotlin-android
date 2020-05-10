package com.gmail.jiangyang5157.android.router.fragment

import androidx.fragment.app.FragmentActivity
import com.gmail.jiangyang5157.android.router.error.RouterException

interface FragmentActivityExtension

internal fun FragmentActivityExtension.expectThisToBeAFragmentActivity() =
    this as? FragmentActivity ?: throw RouterException(
        "${RouterFragmentActivity::class.java.simpleName} only works for androidx.fragment.app.FragmentActivity"
    )
