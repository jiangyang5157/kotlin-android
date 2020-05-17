package com.gmail.jiangyang5157.kotlin.example.router.fragmentroute

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.DataRoute
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.android.router.fragment.FragmentRoute
import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KClass

interface ExampleRoute : FragmentRoute, DataRoute<String>, ParcelableRoute

@Parcelize
class ExampleRoute1(override val data: String) : ExampleRoute {

    override val fragment: KClass<out Fragment>
        get() = ExampleFragment1::class

}

@Parcelize
class ExampleRoute2(override val data: String) : ExampleRoute {

    override val fragment: KClass<out Fragment>
        get() = ExampleFragment2::class

}
