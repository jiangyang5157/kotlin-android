package com.gmail.jiangyang5157.architecture.ext

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Created by Yang Jiang on May 06, 2019
 *
 * Runs a FragmentTransaction, then calls commit().
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}
