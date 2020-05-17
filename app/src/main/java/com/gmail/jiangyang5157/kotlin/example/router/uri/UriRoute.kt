package com.gmail.jiangyang5157.kotlin.example.router.uri

import android.net.Uri
import com.gmail.jiangyang5157.android.router.core.DataRoute
import com.gmail.jiangyang5157.android.router.core.KeyRoute
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.parcel.Parcelize

@Parcelize
class UriRoute(override val data: String) : KeyRoute, DataRoute<String>, ParcelableRoute {

    override val key: Key
        get() {
            val uri = Uri.parse(data)
            return Key("${uri.scheme}://${uri.authority}${uri.path}")
        }

    fun parameter(name: String): String? = Uri.parse(data).getQueryParameter(name)
}
