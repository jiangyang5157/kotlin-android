package com.gmail.jiangyang5157.kotlin

import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouter
import com.gmail.jiangyang5157.android.router.fragment.FragmentRouterBuilder
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.core.util.ViewModelFactory
import com.gmail.jiangyang5157.kotlin.ui.MainActivity
import com.gmail.jiangyang5157.kotlin.ui.router.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [AppInjection::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAppExecutor(): AppExecutor = AppExecutor()

    @Provides
    @Singleton
    fun provideFragmentRouterBuilderForRouterActivity(): FragmentRouterBuilder<RouterActivityRoute> {
        return FragmentRouterBuilder(RouterActivityRoute::class)
    }

    @Provides
    @Singleton
    fun provideFragmentRouterForRouterActivity(builder: FragmentRouterBuilder<RouterActivityRoute>): FragmentRouter<RouterActivityRoute> {
        builder.transitions {
            register(RouterFragmentTransition())
        }
        builder.transitions {
            register(RouterFragment1And3Transition())
        }
        builder.routing {
            route<Route0> { RouterFragment0::class }
            route<Route1> { RouterFragment1::class }
        }
        builder.routing {
            route<Route2> { RouterFragment2::class }
            route<Route3> { RouterFragment3::class }
        }
        return builder.build()
    }
}

@Module()
abstract class AppInjection {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeRouterActivity(): RouterActivity

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeRouterFragment0(): RouterFragment0

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeRouterFragment1(): RouterFragment1

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeRouterFragment2(): RouterFragment2

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeRouterFragment3(): RouterFragment3

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
