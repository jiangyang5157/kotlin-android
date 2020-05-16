package com.gmail.jiangyang5157.android.router.core

/**
 * # RoutingStackInstruction
 * A [Router] can execute arbitrary changes in the [RoutingStack].
 * All instructions that one can send to a [Router] are represented as a function from the "current" [RoutingStack] to the "desired" one.
 *
 * ## Note
 * - This function has to be pure
 * - This function should not have any side-effects
 * - This function should not mutate the "current" [RoutingStack], but create a new one.
 */
typealias RoutingStackInstruction<T> = RoutingStack<T>.() -> RoutingStack<T>

/**
 * @return A combination of the receiver instruction and the [other] instruction.
 * This combination will simply chain the instructions like
 * ```
 * stack -> (receiver) -> stack -> (other) -> stack
 * ```
 */
operator fun <T : Route> RoutingStackInstruction<T>.plus(other: RoutingStackInstruction<T>): RoutingStackInstruction<T> =
    { other(this@plus.invoke(this)) }


/**
 * Creates an instance of [RoutingStackInstruction] that represents a "noop" by just retuning the [RoutingStack] as it is.
 */
@Suppress("FunctionName")
fun <T : Route> emptyRouterInstruction(): RoutingStackInstruction<T> =
    { this }

/**
 * # RouterInstructionExecutor
 * Can receive [RoutingStackInstruction]s and will execute them.
 *
 * ## Note
 * - Instructions are not guaranteed to be executed immediately
 * - Instructions will be called in the order they were dispatched
 *
 * @see Router
 */
interface RouterInstructionExecutor<T : Route> {

    /**
     * Will execute a [RoutingStackInstruction] which represents any arbitrary change of the [RoutingStack] by creating a new [RoutingStack]
     *
     * @see RoutingStackInstruction
     */
    fun routingStackInstruction(instruction: RoutingStackInstruction<T>)
}
