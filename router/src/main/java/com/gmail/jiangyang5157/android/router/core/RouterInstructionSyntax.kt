package com.gmail.jiangyang5157.android.router.core

/**
 * # RouterInstruction
 * A [Router] can execute arbitrary changes in the [RoutingStack].
 * All instructions that one can send to a [Router] are represented as a function from the "current" [RoutingStack] to the "desired" one.
 *
 * ## Note
 * - This function has to be pure
 * - This function should not have any side-effects
 * - This function should not mutate the "current" [RoutingStack], but create a new one.
 */
typealias RouterInstruction<T> = RoutingStack<T>.() -> RoutingStack<T>

/**
 * # RouterInstructionSyntax
 * Can receive [RouterInstruction]s and will execute them.
 *
 * ## Note
 * - Instructions are not guaranteed to be executed immediately
 * - Instructions will be called in the order they were dispatched
 *
 * @see Router
 */
interface RouterInstructionSyntax<T : Route> {

    /**
     * Will execute a [RouterInstruction] which represents any arbitrary change of the [RoutingStack] by creating a new [RoutingStack]
     *
     * @see Router
     * @see RouterInstruction
     * @see RouterInstructionSyntax
     */
    fun routerInstruction(instruction: RouterInstruction<T>)
}

/**
 * @return A combination of the receiver instruction and the [other] instruction.
 * This combination will simply chain the instructions like
 * ```
 * stack -> (receiver) -> stack -> (other) -> stack
 * ```
 */
operator fun <T : Route> RouterInstruction<T>.plus(other: RouterInstruction<T>): RouterInstruction<T> =
    { other(this@plus.invoke(this)) }


/**
 * Creates an instance of [RouterInstruction] that represents a "noop" by just retuning the [RoutingStack] as it is.
 */
@Suppress("FunctionName")
fun <T : Route> EmptyRouterInstruction(): RouterInstruction<T> =
    { this }
