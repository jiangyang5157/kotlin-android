package com.gmail.jiangyang5157.android.router.fragment

import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import com.gmail.jiangyang5157.android.router.core.emptyRouterInstruction
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RoutingStackInstruction
import com.gmail.jiangyang5157.android.router.core.plus
import com.gmail.jiangyang5157.android.router.error.RouterException
import com.gmail.jiangyang5157.android.router.fragment.transition.EmptyFragmentTransition
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransition
import com.gmail.jiangyang5157.android.router.fragment.transition.FragmentTransitionBuilder
import com.gmail.jiangyang5157.android.router.fragment.transition.plus
import kotlin.reflect.KClass

@FragmentRouterDsl
class FragmentRouterBuilder<T : Route>(private val type: KClass<T>) {

    private val typeIsParcelable = Parcelable::class.java.isAssignableFrom(type.java)

    private var initialInstruction: RoutingStackInstruction<T> = emptyRouterInstruction()

    private var fragmentStackPatcher: FragmentStackPatcher = FragmentStackPatcherImpl

    private var fragmentMap: FragmentMap<T> = EmptyFragmentMap()

    private var fragmentTransition: FragmentTransition = EmptyFragmentTransition

    private var fragmentRouteStorage: FragmentRouteStorage<T>? = when {
        typeIsParcelable -> ParcelableFragmentRouteStorage.createUnsafe()
        else -> null
    }

    private var saveRoutingStack: SaveRoutingStack<T>? = when {
        typeIsParcelable -> ParcelableSaveRoutingStack.createUnsafe()
        else -> null
    }

    private var fragmentContainerLifecycleFactory: FragmentContainerLifecycle.Factory =
        FragmentContainerLifecycleImpl.Factory(
            attachEvent = Lifecycle.Event.ON_RESUME,
            detachEvent = Lifecycle.Event.ON_PAUSE
        )

    /**
     * Allows for configuration of the lifecycle events that shall be used to attach/detach the fragment container.
     *
     * Default:
     * - attach on `ON_RESUME`
     * - detach on  `ON_PAUSE`
     */
    fun fragmentContainerLifecycle(init: GenericFragmentContainerLifecycleBuilder.() -> Unit) {
        this.fragmentContainerLifecycleFactory =
            GenericFragmentContainerLifecycleBuilder()
                .also(init).build()
    }

    /**
     * Specify a custom [FragmentStackPatcher]
     *
     * [FragmentStackPatcherImpl] will be used as default
     *
     * @see FragmentStackPatcher
     * @see FragmentStackPatcherImpl
     */
    @FragmentRouterDsl
    fun fragmentStackPatcher(patcher: FragmentStackPatcher) {
        this.fragmentStackPatcher = patcher
    }

    /**
     * Specify a custom [FragmentRouteStorage].
     *
     * [ParcelableFragmentRouteStorage] will be used as default if the base route type [T] is [Parcelable]
     *
     * @see FragmentRouteStorage
     * @see ParcelableFragmentRouteStorage
     */
    @FragmentRouterDsl
    fun fragmentRouteStorage(storage: FragmentRouteStorage<T>) {
        this.fragmentRouteStorage = storage
    }

    /**
     * Specify a custom [saveRoutingStack]
     *
     * [ParcelableSaveRoutingStack] will be used as default if the base route type [T] is [Parcelable]
     *
     * @see SaveRoutingStack
     * @see ParcelableFragmentRouteStorage
     */
    @FragmentRouterDsl
    fun fragmentRoutingStackBundler(bundler: SaveRoutingStack<T>) {
        this.saveRoutingStack = bundler
    }

    @FragmentRouterDsl
    fun routing(init: FragmentMapBuilder<T>.() -> Unit) {
        this.fragmentMap += FragmentMapBuilder<T>()
            .also(init).build()
    }

    @FragmentRouterDsl
    fun transitions(init: FragmentTransitionBuilder.() -> Unit) {
        this.fragmentTransition += FragmentTransitionBuilder()
            .also(init).build()
    }

    @FragmentRouterDsl
    fun initialize(instruction: RoutingStackInstruction<T>) {
        this.initialInstruction += instruction
    }

    fun build(): FragmentRouter<T> =
        FragmentRouter(
            fragmentMap = fragmentMap,
            fragmentRouteStorage = requireFragmentRouteStorage(),
            saveRoutingStack = requireSaveRoutingStack(),
            fragmentTransition = fragmentTransition,
            fragmentStackPatcher = fragmentStackPatcher,
            fragmentContainerLifecycleFactory = fragmentContainerLifecycleFactory,
            initialInstruction = initialInstruction
        )

    private fun requireFragmentRouteStorage(): FragmentRouteStorage<T> =
        fragmentRouteStorage ?: throw RouterException(
            """
                Missing `FragmentRouteStorage`!
                Either specify one with

                    FragmentRouter {
                        ...
                        fragmentRouteStorage(MyFragmentRouteStorage())
                    }

                Or let ${type.java.simpleName} implement `Parcelable` to use the default implementation
            """.trimIndent()
        )

    private fun requireSaveRoutingStack(): SaveRoutingStack<T> =
        saveRoutingStack ?: throw RouterException(
            """
                Missing `SaveRoutingStack`!
                Either specify one with

                    FragmentRouter {
                        ...
                        fragmentRoutingStackBundler(MySaveRoutingStack())
                    }

                Or let ${type.java.simpleName} implement `Parcelable` to use the default implementation
            """.trimIndent()
        )
}
