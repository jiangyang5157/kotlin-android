package com.gmail.jiangyang5157.android.router.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentContainer(
    val activity: FragmentActivity,
    val fragmentManager: FragmentManager,
    @IdRes val id: Int
)
