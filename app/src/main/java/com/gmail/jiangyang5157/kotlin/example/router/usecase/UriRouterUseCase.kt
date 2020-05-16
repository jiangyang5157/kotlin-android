package com.gmail.jiangyang5157.kotlin.example.router.usecase

import android.net.Uri
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

class UriRoutePatch(
    override val routeDataClass: KClass<out RouteData<String>>,
    override val key: Key
) : RoutePatch<String>

class UriRoutePack(override val data: String) : RoutePack<String> {
    override val key: Key
        get() {
            val uri = Uri.parse(data)
            return Key("${uri.scheme}://${uri.authority}${uri.path}")
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
