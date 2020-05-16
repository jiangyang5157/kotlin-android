package com.gmail.jiangyang5157.android.router.core

/**
 * # Router
 * A [Router] controls one region of the screen, that can display distinct routes.
 * It is common to have one router for the whole screen.
 * e.g.: This router might display a `LoginRoute` if the user is not logged in, or a `HomeRoute` once the user registered.
 *
 * ## Example Usage
 * - Is user logged in?
 * ```
 * if(user.isLoggedIn) {
 *     router.push(HomeRoute())
 * } else {
 *     router.push(LoginRoute())
 * }
 * ```
 * - Go back by one route
 * ```
 * fun onBackPressed() {
 *     router.pop()
 * }
 * ```
 * - Example (advanced): Replace current route
 * ```
 * router {
 *     pop().push(NewRoute())
 * }
 * ```
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
