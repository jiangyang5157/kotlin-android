package com.gmail.jiangyang5157.example_core.app

import android.app.Application
import com.gmail.jiangyang5157.feature_color.ColorModule
import com.gmail.jiangyang5157.example_core.AppModule
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
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun module(module: ColorModule): Builder

        fun build(): AppComponent
    }
}
