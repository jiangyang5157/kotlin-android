package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.view.View
import android.view.ViewGroup

fun View.flatViewHierarchy(): List<View> {
    return listOf(this).plus(children.flatMap { it.flatViewHierarchy() })
}

val View.children
    get() = when (this) {
        is ViewGroup -> this.children
        else -> emptyList<View>()
    }
