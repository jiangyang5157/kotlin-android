package com.gmail.jiangyang5157.kotlin_android_kit.ext

import android.util.Log

/**
 * Created by Yang Jiang on April 24, 2018
 */

inline val Any.tag: String
    get() = javaClass.simpleName

fun Any.v(msg: String) = v(tag, msg)

fun Any.d(msg: String) = d(tag, msg)

fun Any.i(msg: String) = i(tag, msg)

fun Any.w(msg: String) = w(tag, msg)

fun Any.e(msg: String) = e(tag, msg)

fun Any.wtf(msg: String) = wtf(tag, msg)

private fun v(tag: String, msg: String) = Log.v(tag, msg)

private fun d(tag: String, msg: String) = Log.d(tag, msg)

private fun i(tag: String, msg: String) = Log.i(tag, msg)

private fun w(tag: String, msg: String) = Log.w(tag, msg)

private fun e(tag: String, msg: String) = Log.e(tag, msg)

private fun wtf(tag: String, msg: String) = Log.wtf(tag, msg)
