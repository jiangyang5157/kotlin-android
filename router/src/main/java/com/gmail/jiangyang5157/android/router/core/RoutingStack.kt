package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.kotlin_kit.model.Key
import kotlin.jvm.JvmName

/**
 * # RoutingStack
 * Represents a "routing state" where the most top (last) [Element] is representing the currently "active" (displayed) route
 * and the most bottom (first) [Element] the "root".
 * The two most common operations of a [RoutingStack] are "push" and "pop"
 *
 * - push: Adds a route to the top of the stack.
 * e.g. navigating from the "HomeRoute" to the "SettingsRoute"
 * - pop: Removes the currently "active" route from the top of this stack
 * e.g. pressing a back button in the "SettingsRoute" will lead to the "HomeRoute" getting active again
 *
 * ## Note
 * - Implementations of [RoutingStack] should implement a [equals] and [hashCode] function that makes [RoutingStack]'s comparable
 * - Implementations of [RoutingStack] should always be implemented *immutable*
 *
 * @see [RoutingStackInstructionSyntax] for "push" and "pop" details
 */
interface RoutingStack<T : Route> :
    RoutingStackInstructionSyntax<T, RoutingStack<T>>,
    Iterable<RoutingStack.Element<T>> {

    /**
     * All routes that represent this routing stack.
     * This routes are stored as [Element] which makes routes identifiable by associating each entry in an stack with a [Key]
     *
     * @see RoutingStack
     * @see Element
     */
    val elements: List<Element<T>>

    override fun iterator(): Iterator<Element<T>> =
        elements.iterator()

    /**
     * Creates a new [RoutingStack] based on the specified [instruction].
     *
     * @param instruction A simple function that describes how a new stack can be created from current stack
     *
     * @see with
     * @see RoutingStack
     */
    override fun routingStackInstruction(instruction: RoutingStackInstruction<T>): RoutingStack<T> =
        with(elements.instruction())

    /**
     * @return
     * - A new [RoutingStack] that contains the specified [elements]
     * - This [RoutingStack] if the [elements] did not change.
     *
     * ## Note
     * - [RoutingStack] implementations are required to be immutable
     */
    fun with(elements: Iterable<Element<T>> = this.elements): RoutingStack<T>

    /**
     * # RoutingStack.Element
     * Represents one entry of the [RoutingStack] that is able to identify each given route by a unique [Key], so that even if the routes in the stack are not distinct, the elements are!
     *
     * ## Note
     * - Elements are compared by route and key, thus the behaviour of the [equals] and [hashCode] functions are guaranteed to behave consistently for all implementations.
     *
     * @see Key
     * @see Route
     * @see RoutingStack
     */
    abstract class Element<out T : Route> {

        /**
         * Unique [Key] that can be used to identify the [Element] inside a [RoutingStack]
         *
         * @see Element
         */
        abstract val key: Key

        /**
         * The route associated with the element.
         *
         * # Note
         * - Routes are not required to be distinct in a [RoutingStack]. Use [key] to properly identify elements in the [RoutingStack]
         */
        abstract val route: T

        override fun toString(): String =
            "Element(key=${key.value}, route=$route)"

        final override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Element<*>) return false
            if (this.key != other.key) return false
            if (this.route != other.route) return false
            return true
        }

        final override fun hashCode(): Int {
            var hashCode = 31
            hashCode += 31 * this.key.hashCode()
            hashCode += 31 * this.route.hashCode()
            return hashCode
        }

        companion object Factory {

            /**
             * @return A default implementation of [Element] for the given [route] and [key]
             */
            operator fun <T : Route> invoke(route: T, key: Key = Key()): Element<T> =
                ElementImpl(route, key)
        }
    }

    companion object Factory {

        fun <T : Route> empty(): RoutingStack<T> =
            RoutingStackImpl(emptyList())

        fun <T : Route> just(element: T): RoutingStack<T> =
            RoutingStackImpl(listOf(element).toElements())

        @JvmName("from")
        fun <T : Route> from(vararg elements: T): RoutingStack<T> =
            RoutingStackImpl(elements.toList().toElements())

        @JvmName("fromIterable")
        fun <T : Route> from(elements: Iterable<T>): RoutingStack<T> =
            RoutingStackImpl(elements.toList().toElements())

        @JvmName("fromArray")
        fun <T : Route> from(elements: Array<T>): RoutingStack<T> =
            RoutingStackImpl(elements.toList().toElements())

        @JvmName("fromSequence")
        fun <T : Route> from(elements: Sequence<T>): RoutingStack<T> =
            RoutingStackImpl(elements.toList().toElements())
    }
}

/**
 * Convenience function to work on the [Route] objects directly
 */
val <T : Route> RoutingStack<T>.routes
    get() = elements.map(RoutingStack.Element<T>::route)

/**
 * @return Whether or not the [RoutingStack] contains the specified [key]
 */
operator fun RoutingStack<*>.contains(key: Key): Boolean =
    this.elements.any { it.key == key }

/**
 * @return Whether or not the [RoutingStack] contains the specified [element].
 *
 * ## Note
 * - The element will be compared by route and key (not just key)
 *
 * @see RoutingStack.Element
 */
operator fun RoutingStack<*>.contains(element: RoutingStack.Element<*>): Boolean =
    this.elements.any { it == element }

/**
 * @return Whether or not the [RoutingStack] contains the specified [route]
 *
 * ## Note
 * - Routes may not be distinct in the [RoutingStack] It is possible, that the a stack contains a given route multiple times
 */
operator fun RoutingStack<*>.contains(route: Route): Boolean =
    this.routes.contains(route)

private fun <T : Route> Iterable<T>.toElements() =
    this.map { element -> ElementImpl(element) }

/**
 * @return
 * - A default implementation of [RoutingStack.Element] for this given route with the specified [key].
 * - If no [key] was specified, then a new random key will be created.
 */
fun <T : Route> T.asElement(key: Key = Key()) =
    RoutingStack.Element(this, key)
