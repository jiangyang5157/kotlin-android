package com.gmail.jiangyang5157.kotlin.example.router.uri

import android.net.Uri
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import kotlin.reflect.KClass

interface UriRoute : Route, Parcelable

data class UriRouteMeta(
    val fragmentClass: KClass<out Fragment>,
    val routeClass: KClass<out UriRoute>,
    val id: String
) {

    fun accept(uriString: String): Boolean {
        val anotherUri = Uri.parse(uriString)
        val uri = Uri.parse(id)
        return anotherUri.scheme == uri.scheme &&
            anotherUri.authority == uri.authority &&
            anotherUri.path == uri.path
    }
}
