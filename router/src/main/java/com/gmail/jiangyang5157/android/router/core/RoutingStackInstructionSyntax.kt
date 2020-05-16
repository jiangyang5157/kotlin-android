package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.model.Key

/**
 * # RoutingStackInstruction
 * Function that describes a manipulation of a [RoutingStack] by receiving a list of elements to create a new list.
 */
typealias RoutingStackInstruction<T> = List<RoutingStack.Element<T>>.() -> Iterable<RoutingStack.Element<T>>

@RoutingStackDsl
interface RoutingStackInstructionSyntax<T : Route, R> {
    fun routingStackInstruction(instruction: RoutingStackInstruction<T>): R
}

/**
 * Will remove all routes from the stack
 */
@RoutingStackDsl
fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.clear(): R =
    routingStackInstruction {
        emptyList()
    }

/**
 * Will push the [element] to the top of the stack
 *
 * ## Note
 * - Since element keys are required to be distinct in the routing stack, an element with the same key will be removed from the stack before pushing the new element to the top
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.push(element: RoutingStack.Element<T>): R =
    routingStackInstruction {
        filterNot { it.key == element.key } + element
    }

/**
 * Will push the [route] to the top of the stack.
 *
 * ## Note
 * - This operation will create [RoutingStack.Element] with random [Key]
 * - This operation is not distinct: Meaning, that if the [route] is already present in the stack, it will simply be duplicated.
 *
 * @see pushDistinct
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.push(route: T): R =
    routingStackInstruction {
        this + RoutingStack.Element(route)
    }

/**
 * Will push the [route] to the top of the stack, but will make sure that the route is only present once in the stack.
 *
 * ## Note
 * - All occurrences of the [route] in the current stack will be removed so that the route is just present at the top
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.pushDistinct(route: T): R =
    routingStackInstruction {
        val top = lastOrNull { it.route == route } ?: RoutingStack.Element(route)
        filterNot { it.route == route } + top
    }

/**
 * Will pop the top/active route if the routing stack is not empty
 */
@RoutingStackDsl
fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.pop(): R =
    routingStackInstruction {
        if (isEmpty()) {
            emptyList()
        } else {
            subList(0, lastIndex)
        }
    }

/**
 * Will pop all routes from the top until the condition is hit (while the element that fulfills the condition is not popped)
 */
@RoutingStackDsl
inline infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.popUntil(crossinline predicate: (RoutingStack.Element<T>) -> Boolean): R =
    routingStackInstruction {
        if (isEmpty()) {
            emptyList()
        } else {
            dropLastWhile { element ->
                !predicate(element)
            }
        }
    }

/**
 * Will pop all routes from the top until the specified [route], while the given [route] itself is not popped
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.popUntilElement(element: RoutingStack.Element<T>): R =
    popUntil {
        it == element
    }

/**
 * Will pop all routes from the top until the specified [route], while the given [route] itself is not popped
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.popUntilRoute(route: T): R =
    popUntil {
        it.route == route
    }


/**
 * Will pop all routes from the top until the specified [key], while the given route with same [key] itself is not popped
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.popUntilKey(key: Key): R =
    popUntil { it.key == key }

/**
 * Will replace the current `top` route with the new route.
 *
 * #Note
 * - This operation is distinct in key. An element with the same key will be removed from the routing stack
 * - This is effectively just a chained `pop().push(route)
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.replaceTopWith(element: RoutingStack.Element<T>): R =
    routingStackInstruction {
        if (isEmpty()) {
            listOf(element)
        } else {
            dropLast(1).filter { it.key != element.key }.plus(element)
        }
    }

/**
 * Will replace the current `top` route with the new route.
 *
 * ## Note
 * - Like [push]: This operation is not distinct. If the route is already present in the routing stack, it will be duplicated (unless it already was the top route)
 * - This is effectively just a chained `pop().push(route)`
 */
@RoutingStackDsl
infix fun <T : Route, R> RoutingStackInstructionSyntax<T, R>.replaceTopWith(route: T): R =
    replaceTopWith(
        RoutingStack.Element(route)
    )
