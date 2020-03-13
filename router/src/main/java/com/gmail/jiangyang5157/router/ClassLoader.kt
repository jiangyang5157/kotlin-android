package com.gmail.jiangyang5157.router

import android.content.Intent
import androidx.fragment.app.Fragment

private inline fun <reified T : Any> Any.castOrNull() = this as? T

private val classMap = mutableMapOf<String, Class<*>>()

internal fun <T> String.loadClassOrNull(): Class<out T>? =
    classMap.getOrPut(this) {
        try {
            Class.forName(this)
        } catch (e: ClassNotFoundException) {
            return null
        }
    }.castOrNull()

internal fun String.loadFragmentOrNull(): Fragment? =
    try {
        this.loadClassOrNull<Fragment>()?.newInstance()
    } catch (e: ClassNotFoundException) {
        null
    }

private fun intentTo(packageName: String, className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(packageName, className)

internal fun String.loadIntentOrNull(packageName: String): Intent? =
    try {
        Class.forName(this).run { intentTo(packageName, this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }
