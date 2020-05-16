package com.gmail.jiangyang5157.android.router.core

/**
 * # Router
 * A [Router] controls one region of the screen, that can display distinct routes.
 *
 * ## Note
 * - Instructions sent to a [Router] are not guaranteed to be executed immediately
 * - Instructions sent to a [Router] are not guaranteed to be called just once
 * - Instructions sent to a [Router] will be executed in the order they were dispatched
 * - Instructions sent to a [Router] that is currently not attached will be executed as soon as the router gets attached
 */
interface Router<T : Route> :
    RouterInstructionSyntax<T>,
    RoutingStackInstructionSyntax<T, Unit> {

    override fun routingStackInstruction(instruction: RoutingStackInstruction<T>) =
        routerInstruction { this.routingStackInstruction(instruction) }

    /**
     * Just syntactic sugar for [RouterInstructionSyntax.routerInstruction]
     *
     * @see RouterInstructionSyntax.routerInstruction
     */
    operator fun invoke(instruction: RouterInstruction<T>): Unit =
        routerInstruction(instruction)
}
