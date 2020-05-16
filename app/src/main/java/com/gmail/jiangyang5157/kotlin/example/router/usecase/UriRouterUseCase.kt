package com.gmail.jiangyang5157.kotlin.example.router.usecase

import android.net.Uri
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.parcel.Parcelize

@Parcelize
class UriRouteData(override val data: String) : RouteData<String> {

    fun getParam(name: String): String? =
        Uri.parse(data).getQueryParameter(name)
}

class UriRouteElement(uriString: String) : RouteElement<String, UriRouteData>() {

    override val key = getKeyFromUriString(uriString)

    override val route = UriRouteData(uriString)

    private fun getKeyFromUriString(uriString: String): Key {
        val uri = Uri.parse(uriString)
        return Key("${uri.scheme}://${uri.authority}${uri.path}")
    }
}
