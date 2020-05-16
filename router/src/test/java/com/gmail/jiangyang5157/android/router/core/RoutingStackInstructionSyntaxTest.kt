package com.gmail.jiangyang5157.android.router.core

import kotlin.test.Test
import kotlin.test.assertEquals

class RoutingStackInstructionSyntaxTest {

    private data class RouteImpl1(val id: Int) : Route

    private data class RouteImpl2(val id: String) : Route

    @Test
    fun size_sameRouteType_diffRouteValue_diffKey() {
        val stack = RoutingStack.from(
            RouteImpl1(0),
            RouteImpl1(1)
        )
        assertEquals(2, stack.elements.size)
    }

    @Test
    fun size_diffRouteType_diffKey() {
        val stack = RoutingStack.from(
            RouteImpl1(0),
            RouteImpl2("1")
        )
        assertEquals(2, stack.elements.size)
    }


    @Test
    fun size_sameRouteType_sameRouteValue_diffKey() {
        val stack = RoutingStack.from(
            RouteImpl1(0),
            RouteImpl1(0)
        )
        assertEquals(2, stack.elements.size)
    }

    @Test
    fun clear_diffRouteType_diffKey() {
        val stack = RoutingStack.from(
            RouteImpl1(0),
            RouteImpl2("1")
        )
        val newStack = stack.clear()
        assertEquals(2, stack.elements.size)
        assertEquals(0, newStack.elements.size)
    }


    @Test
    fun push_sameRouteType_diffRouteValue_diffKey_expectAddToTop() {
        val stack = RoutingStack.from(
            RouteImpl1(0),
            RouteImpl1(1)
        )
        val newStack = stack.push(RouteImpl1(2)) // 0, 1, 2
        assertEquals(2, stack.elements.size)
        assertEquals(3, newStack.elements.size)
        assertEquals(RouteImpl1(2), newStack.elements[2].route)
    }




    @Test
    fun push() {
        val stack = RoutingStackImpl(emptyList())
        val newStack = stack.push(RouteImpl1(0))
        assertEquals(1, newStack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements.first().route)
    }

    @Test
    fun pushDistinct() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1), RouteImpl1(2))
        val newStack = stack.pushDistinct(RouteImpl1(1))
        assertEquals(3, newStack.elements.size)
        assertEquals(0, newStack.elements[0].route.id)
        assertEquals(2, newStack.elements[1].route.id)
        assertEquals(1, newStack.elements[2].route.id)
    }

    @Test
    fun pop() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1), RouteImpl1(2))
        val newStack = stack.pop()
        assertEquals(3, stack.elements.size)
        assertEquals(2, newStack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements[0].route)
        assertEquals(RouteImpl1(1), newStack.elements[1].route)
    }

    @Test
    fun pop_whenEmpty() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1), RouteImpl1(2))
        val newStack = stack.pop().pop().pop().pop()
        assertEquals(3, stack.elements.size)
        assertEquals(0, newStack.elements.size)
    }

    @Test
    fun clear() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1))
        val newStack = stack.clear()
        assertEquals(2, stack.elements.size)
        assertEquals(0, newStack.elements.size)
    }

    @Test
    fun popUntil() {
        val stack =
            RoutingStack.from(
                RouteImpl1(0),
                RouteImpl1(1),
                RouteImpl1(2),
                RouteImpl1(1),
                RouteImpl1(2)
            )
        val newStack = stack.popUntil { route -> route == RouteImpl1(1) }
        assertEquals(5, stack.elements.size)
        assertEquals(4, newStack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements[0].route)
        assertEquals(RouteImpl1(1), newStack.elements[1].route)
        assertEquals(RouteImpl1(2), newStack.elements[2].route)
        assertEquals(RouteImpl1(1), newStack.elements[3].route)
    }

    @Test
    fun popUntilRoute() {
        val stack =
            RoutingStack.from(
                RouteImpl1(0),
                RouteImpl1(1),
                RouteImpl1(2),
                RouteImpl1(1),
                RouteImpl1(2)
            )
        val newStack = stack.popUntilRoute(RouteImpl1(1))
        assertEquals(5, stack.elements.size)
        assertEquals(4, newStack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements[0].route)
        assertEquals(RouteImpl1(1), newStack.elements[1].route)
        assertEquals(RouteImpl1(2), newStack.elements[2].route)
        assertEquals(RouteImpl1(1), newStack.elements[3].route)
    }

    @Test
    fun push_element() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1))
        val newStack = stack.push(DefaultElement(RouteImpl1(2)))
        assertEquals(2, stack.elements.size)
        assertEquals(3, newStack.elements.size)
        assertEquals(RouteImpl1(2), newStack.elements[2].route)
    }

    @Test
    fun push_element_whichIsAlreadyInStack() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1))
        val newStack = stack.push(stack.elements.first())
        assertEquals(2, stack.elements.size)
        assertEquals(2, newStack.elements.size)
        assertEquals(RouteImpl1(1), newStack.elements[0].route)
        assertEquals(RouteImpl1(0), newStack.elements[1].route)
    }

    @Test
    fun replaceTopWith_onEmptyStack() {
        val stack = RoutingStack.empty<Route>()
        val newStack = stack.replaceTopWith(RouteImpl1(0))
        assertEquals(1, newStack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements.first().route)
    }

    @Test
    fun replaceTopWith_onNonEmptyStack() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1), RouteImpl1(2))
        val newStack = stack.replaceTopWith(RouteImpl1(3))
        assertEquals(3, stack.elements.size)
        assertEquals(RouteImpl1(0), newStack.elements[0].route)
        assertEquals(RouteImpl1(1), newStack.elements[1].route)
        assertEquals(RouteImpl1(3), newStack.elements[2].route)
    }

    @Test
    fun replaceTopWith_onNonEmptyStack_withDuplicateKey() {
        val stack = RoutingStack.from(RouteImpl1(0), RouteImpl1(1), RouteImpl1(2))
        val newStack =
            stack.replaceTopWith(RoutingStack.Element(RouteImpl1(3), key = stack.first().key))
        assertEquals(2, newStack.elements.size)
        assertEquals(RouteImpl1(1), newStack.elements[0].route)
        assertEquals(RouteImpl1(3), newStack.elements[1].route)
    }
}
