package com.gmail.jiangyang5157.core.ext

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson<T>(json, object : TypeToken<T>() {}.type)
