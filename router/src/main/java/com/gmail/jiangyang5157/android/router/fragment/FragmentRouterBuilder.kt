package com.gmail.jiangyang5157.android.router.fragment

import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import com.gmail.jiangyang5157.android.router.core.EmptyRouterInstruction
import com.gmail.jiangyang5157.android.router.core.Route
import com.gmail.jiangyang5157.android.router.core.RouterInstruction
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

    private var initialInstruction: RouterInstruction<T> = EmptyRouterInstruction()

    private var fragmentStackPatcher: FragmentStackPatcher = FragmentStackPatcherImpl

    private var fragmentMap: FragmentMap<T> = EmptyFragmentMap()

    private var fragmentTransition: FragmentTransition = EmptyFragmentTransition

    private var fragmentRouteStorageSyntax: FragmentRouteStorageSyntax<T>? = when {
        typeIsParcelable -> ParcelableFragmentRouteStorageSyntax.createUnsafe()
        else -> null
    }

    private var fragmentRoutingStackBundleSyntax: FragmentRoutingStackBundleSyntax<T>? = when {
        typeIsParcelable -> ParcelableFragmentRoutingStackBundleSyntax.createUnsafe()
        else -> null
    }

    private var fragmentContainerLifecycleFactory: FragmentContainerLifecycle.Factory =
        GenericFragmentContainerLifecycle.Factory(
            attachEvent = Lifecycle.Event.ON_RESUME,
            detachEvent = Lifecycle.Event.ON_PAUSE
        )

    /**
     * Specify a custom [FragmentStackPatcher]
     *
     * @see FragmentStackPatcher
     * @see FragmentStackPatcherImpl
     */
    @FragmentRouterDsl
    fun fragmentStackPatcher(patcher: FragmentStackPatcher) {
        this.fragmentStackPatcher = patcher
    }

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
     * Specify a custom [FragmentRouteStorageSyntax].
     *
     * [ParcelableFragmentRouteStorageSyntax] will be used as default if the base route type [T] is [Parcelable]
     *
     * @see FragmentRouteStorageSyntax
     * @see ParcelableFragmentRouteStorageSyntax
     */
    @FragmentRouterDsl
    fun fragmentRouteStorage(storage: FragmentRouteStorageSyntax<T>) {
        this.fragmentRouteStorageSyntax = storage
    }

    /**
     * Specify a custom [fragmentRoutingStackBundleSyntax]
     *
     * [ParcelableFragmentRoutingStackBundleSyntax] will be used as default if the base route type [T] is [Parcelable]
     *
     * @see FragmentRoutingStackBundleSyntax
     * @see ParcelableFragmentRouteStorageSyntax
     */
    @FragmentRouterDsl
    fun fragmentRoutingStackBundler(bundler: FragmentRoutingStackBundleSyntax<T>) {
        this.fragmentRoutingStackBundleSyntax = bundler
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
    fun initialize(instruction: RouterInstruction<T>) {
        this.initialInstruction += instruction
    }

    fun build(): FragmentRouter<T> =
        FragmentRouter(
            fragmentMap = fragmentMap,
            fragmentRouteStorageSyntax = requireFragmentRouteStorage(),
            fragmentRoutingStackBundleSyntax = requireFragmentRoutingStackBundler(),
            fragmentTransition = fragmentTransition,
            fragmentStackPatcher = fragmentStackPatcher,
            fragmentContainerLifecycleFactory = fragmentContainerLifecycleFactory,
            initialInstruction = initialInstruction
        )

    private fun requireFragmentRouteStorage(): FragmentRouteStorageSyntax<T> =
        fragmentRouteStorageSyntax ?: throw RouterException(
            """
                Missing `FragmentRouteStorageSyntax`!
                Either specify one with

                    FragmentRouter {
                        ...
                        fragmentRouteStorage(MyFragmentRouteStorageSyntax())
                    }

                Or let ${type.java.simpleName} implement `Parcelable` to use the default implementation
            """.trimIndent()
        )

    private fun requireFragmentRoutingStackBundler(): FragmentRoutingStackBundleSyntax<T> =
        fragmentRoutingStackBundleSyntax ?: throw RouterException(
            """
                Missing `FragmentRoutingStackBundleSyntax`!
                Either specify one with

                    FragmentRouter {
                        ...
                        fragmentRoutingStackBundler(MyFragmentRoutingStackBundleSyntax())
                    }

                Or let ${type.java.simpleName} implement `Parcelable` to use the default implementation
            """.trimIndent()
        )
}
