package com.gmail.jiangyang5157.kotlin.example.router.usecase

import android.net.Uri
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import kotlin.reflect.KClass

//interface UriRouteData : RouteData<String>

class UriRoutePatch(
    override val fragmentClass: KClass<out Fragment>,
    override val routeDataClass: KClass<out RouteData<String>>,
    override val key: KeyImpl
) : RoutePatch<String>

class UriRoutePack(override val data: String) : RoutePack<String> {
    override val key: KeyImpl
        get() {
            val uri = Uri.parse(data)
            return KeyImpl("${uri.scheme}://${uri.authority}${uri.path}")
        }
}

class UriRouterData(
    override val routes: List<RoutePatch<String>>,
    override val router: FragmentRouter<RouteData<String>>
) : RouterData<String> {

    override val routeBuilder: RouteBuilder<String> = object : RouteBuilder<String> {
        override fun build(routerPack: RoutePack<String>): RouteData<String> {
            val first = routes.first { it.key.value == routerPack.key.value }
            return first
                .routeDataClass.java.getDeclaredConstructor(String::class.java)
                .newInstance(routerPack.data)
        }
    }
}
