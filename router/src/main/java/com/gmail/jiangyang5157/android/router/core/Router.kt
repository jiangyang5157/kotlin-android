package com.gmail.jiangyang5157.android.router.core

import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter

/**
 * # Router
 * A [Router] controls distinct routes.
 *
 * ## Note
 * - Instructions sent to a [Router] are not guaranteed to be executed immediately
 * - Instructions sent to a [Router] are not guaranteed to be called just once
 * - Instructions sent to a [Router] will be executed in the order they were dispatched
 * - Instructions sent to a [Router] that is currently not attached will be executed as soon as the router gets attached
 *
 * @see FragmentRouter.State
 */
interface Router<T : Route> :
    RouterInstructionExecutor<T>,
    RoutingStackElementsInstructionExecutor<T, Unit> {

    override fun routingStackElementsInstruction(instruction: RoutingStackElementsInstruction<T>) =
        routingStackInstruction { this.routingStackElementsInstruction(instruction) }

    /**
     * @see RouterInstructionExecutor.routingStackInstruction
     */
    operator fun invoke(instruction: RoutingStackInstruction<T>): Unit =
        routingStackInstruction(instruction)
}
