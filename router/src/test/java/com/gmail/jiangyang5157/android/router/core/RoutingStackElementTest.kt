package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class RoutingStackElementTest {

    private data class RouteImpl1(val id: Int) : Route

    private data class RouteImpl2(val id: String) : Route

    private class Impl1<T : Route>(override val route: T, override val key: Key) :
        RoutingStack.Element<T>()

    private data class Impl2<T : Route>(override val route: T, override val key: Key) :
        RoutingStack.Element<T>()

    @Test
    fun equals_sameRouteType_sameRouteValue_sameKey_expectEqual() {
        val i1 = Impl1(RouteImpl1(0), Key("key"))
        val i2 = Impl2(RouteImpl1(0), Key("key"))
        assertTrue(i1 == i2)
        assertEquals(i1.hashCode(), i2.hashCode())
    }

    @Test
    fun equals_sameRouteType_diffRouteValue_sameKey_expectNotEqual() {
        val i1 = Impl1(RouteImpl1(0), Key("key"))
        val i2 = Impl2(RouteImpl1(1), Key("key"))
        assertFalse(i1 == i2)
        assertNotEquals(i1.hashCode(), i2.hashCode())
    }

    @Test
    fun equals_diffRouteType_sameKey_expectNotEqual() {
        val i1 = Impl1(RouteImpl1(0), Key("key"))
        val i2 = Impl2(RouteImpl2("0"), Key("key"))
        assertFalse(i1 == i2)
        assertNotEquals(i1.hashCode(), i2.hashCode())
    }

    @Test
    fun equals_sameRouteType_sameRouteValue_diffKey_expectNotEqual() {
        val i1 = Impl1(RouteImpl1(0), Key())
        val i2 = Impl2(RouteImpl1(0), Key())
        assertFalse(i1 == i2)
        assertNotEquals(i1.hashCode(), i2.hashCode())
    }
}
