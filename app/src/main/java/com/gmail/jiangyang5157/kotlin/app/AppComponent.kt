package com.gmail.jiangyang5157.kotlin.app

import android.app.Application
import com.gmail.jiangyang5157.feature_color.ColorModule
import com.gmail.jiangyang5157.kotlin.AppModule
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

        // optional, due to AppModule constructor doesn't require parameter
        fun module(module: AppModule): Builder

        fun module(module: ColorModule): Builder

        fun build(): AppComponent
    }
}
