package com.gmail.jiangyang5157.kotlin_android_kit.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Yang Jiang on April 18, 2018
 */

fun androidx.fragment.app.FragmentActivity.addFragmentToActivity(@IdRes containerViewId: Int,
                                                                 fragment: androidx.fragment.app.Fragment, tag:
                                           String? = null) {
    supportFragmentManager.transact {
        add(containerViewId, fragment, tag)
    }
}

fun androidx.fragment.app.FragmentActivity.replaceFragmentInActivity(@IdRes containerViewId: Int,
                                                                     fragment: androidx.fragment.app.Fragment, tag:
                                               String? = null) {
    supportFragmentManager.transact {
        replace(containerViewId, fragment, tag)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun androidx.fragment.app.FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}