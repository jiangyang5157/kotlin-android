package com.gmail.jiangyang5157.android.router.fragment.host

import androidx.fragment.app.FragmentActivity
import com.gmail.jiangyang5157.android.router.error.RouterException

interface AsFragmentActivity

fun AsFragment.isFragmentActivity() = this is FragmentActivity

fun AsFragmentActivity.expectThisToBeAFragmentActivity() =
    this as? FragmentActivity ?: throw RouterException(
        "Expect ${this::class.java.simpleName} is an androidx.fragment.app.FragmentActivity"
    )
