package com.gmail.jiangyang5157.example_core.di

import android.app.Application
import com.gmail.jiangyang5157.feature_color.di.ColorModule
import com.gmail.jiangyang5157.example_core.app.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by Yang Jiang on July 12, 2019
 */
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ColorModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun module(module: ColorModule): Builder

        fun build(): AppComponent
    }
}
