package com.gmail.jiangyang5157.android.router.fragment.transition

import androidx.fragment.app.Fragment
import com.gmail.jiangyang5157.android.router.core.Route
import kotlin.reflect.KClass

@PublishedApi
internal inline fun <
    reified ExitFragment : Fragment, reified ExitRoute : Route,
    reified EnterFragment : Fragment, reified EnterRoute : Route>
    GenericFragmentTransition<ExitFragment, ExitRoute, EnterFragment, EnterRoute>.reified() =
    ReifiedGenericFragmentTransition(
        transition = this,
        enterFragment = EnterFragment::class,
        enterRoute = EnterRoute::class,
        exitFragment = ExitFragment::class,
        exitRoute = ExitRoute::class
    )

@PublishedApi
internal class ReifiedGenericFragmentTransition<
    ExitFragment : Fragment, ExitRoute : Route, EnterFragment : Fragment, EnterRoute : Route>(
    transition: GenericFragmentTransition<ExitFragment, ExitRoute, EnterFragment, EnterRoute>,
    val enterFragment: KClass<EnterFragment>,
    val exitFragment: KClass<ExitFragment>,
    val enterRoute: KClass<EnterRoute>,
    val exitRoute: KClass<ExitRoute>
) :
    GenericFragmentTransition<ExitFragment, ExitRoute, EnterFragment, EnterRoute> by transition
