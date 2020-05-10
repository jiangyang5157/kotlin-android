package com.gmail.jiangyang5157.android.router.fragment.setup

import androidx.fragment.app.FragmentActivity
import com.gmail.jiangyang5157.android.router.error.RouterException
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity

interface FragmentActivityExtension

internal fun FragmentActivityExtension.expectThisToBeAFragmentActivity() =
    this as? FragmentActivity ?: throw RouterException(
        "${RouterFragmentActivity::class.java.simpleName} only works for androidx.fragment.app.FragmentActivity"
    )
