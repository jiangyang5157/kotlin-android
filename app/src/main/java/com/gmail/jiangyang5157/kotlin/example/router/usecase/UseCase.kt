package com.gmail.jiangyang5157.kotlin.example.router.usecase

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.*
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.RouterFragment
import com.gmail.jiangyang5157.android.router.fragment.RouterFragmentActivity
import com.gmail.jiangyang5157.android.router.fragment.expectThisToBeAFragment
import kotlin.reflect.KClass

interface RouteData<T> : ParcelableRoute {
    val data: T
}

interface RoutePack<T> {
    val key: KeyImpl
    val data: T
}

interface RoutePatch<T> {
    val fragmentClass: KClass<out Fragment>
    val routeDataClass: KClass<out RouteData<T>>
    val key: KeyImpl
}

interface RouteBuilder<T> {
    fun build(routerPack: RoutePack<T>): RouteData<T>
}

interface RouterData<T> {
    val routes: List<RoutePatch<T>>
    val router: FragmentRouter<RouteData<T>>
    val routeBuilder: RouteBuilder<T>
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
