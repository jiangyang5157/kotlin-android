package com.gmail.jiangyang5157.example_core.example.router.keyroute.uri

import android.net.Uri
import com.gmail.jiangyang5157.android.router.core.DataRoute
import com.gmail.jiangyang5157.android.router.core.KeyRoute
import com.gmail.jiangyang5157.android.router.core.ParcelableRoute
import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlinx.android.parcel.Parcelize

/**
 * KeyRoute.key -> Fragment mapping configuration either by FragmentRouterBuilder()...build() or DSL:
 *
 * ```
 * FragmentRouter {
 *     fragment {
 *         map(Key("LoginRoute")) { LoginFragment::class }
 *         map(Key("HomeRoute")) { HomeFragment::class }
 *         map(Key("SettingsRoute")) { SettingsFragment::class } 
 *     }
 * }
 * ```
 *
 * [data] as an associated data attached with route which in this case is an uri string that can holds multiple parameters by `&`
 *
 * ## Note
 * - ParcelableRoute only needed for router with default FragmentRouteStorage (see ParcelableFragmentRouteStorage).
 * - A custom route storage can implement in a way that doesn't require parcelable route.
 */
@Parcelize
class UriRoute(override val data: String) : KeyRoute, DataRoute<String>, ParcelableRoute {

    override val key: Key
        get() {
            val uri = Uri.parse(data)
            return Key("${uri.scheme}://${uri.authority}${uri.path}")
        }

    fun parameter(name: String): String? = Uri.parse(data).getQueryParameter(name)
}
