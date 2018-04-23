package com.gmail.jiangyang5157.kotlin_android_kit.ext

/**
 * Created by Yang Jiang on April 24, 2018
 */

inline val Any.tag: String
    get() = javaClass.simpleName
