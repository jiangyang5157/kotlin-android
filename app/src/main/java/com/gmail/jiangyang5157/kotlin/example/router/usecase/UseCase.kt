package com.gmail.jiangyang5157.kotlin.example.router.usecase

import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.fragment.*
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.reflect.KClass

interface RouteData<T> : FragmentRoute, Parcelable {
    val data: T
}

interface RoutePack<T> {
    val key: Key
    val data: T
}

interface RoutePatch<T> {
    val routeDataClass: KClass<out RouteData<T>>
    val key: Key
}

interface RouterData<T> {
    val routes: List<RoutePatch<T>>
    val router: FragmentRouter<RouteData<T>>
    val routeBuilder: RouteBuilder<T>
}

interface RouteBuilder<T> {
    fun build(routerPack: RoutePack<T>): RouteData<T>
}

interface RouterFragmentActivitySupport<T> : RouterFragmentActivity {
    val router: FragmentRouter<RouteData<T>>
    val routeBuilder: RouteBuilder<T>
}

interface RouterFragmentSupport<T> : RouterFragment {
    @Suppress("UNCHECKED_CAST")
    override val router: FragmentRouter<RouteData<T>>
        get() = (expectThisToBeAFragment().activity as RouterFragmentActivitySupport<T>).router
    @Suppress("UNCHECKED_CAST")
    val routeBuilder: RouteBuilder<T>
        get() = (expectThisToBeAFragment().activity as RouterFragmentActivitySupport<T>).routeBuilder
}
