package com.gmail.jiangyang5157.kotlin_android_core.ext

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by Yang Jiang on April 18, 2018
 */

fun AppCompatActivity.addFragmentToActivity(@IdRes containerViewId: Int,
                                            fragment: Fragment, tag:
                                            String? = null) {
    supportFragmentManager.transact {
        add(containerViewId, fragment, tag)
    }
}

fun AppCompatActivity.replaceFragmentInActivity(@IdRes containerViewId: Int,
                                                fragment: Fragment, tag:
                                                String? = null) {
    supportFragmentManager.transact {
        replace(containerViewId, fragment, tag)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}