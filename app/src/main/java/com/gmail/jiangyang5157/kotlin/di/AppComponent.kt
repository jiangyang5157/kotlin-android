package com.gmail.jiangyang5157.kotlin.di

import android.app.Application
import com.gmail.jiangyang5157.architecture.App
import com.gmail.jiangyang5157.kotlin.app.AppModule
import com.gmail.jiangyang5157.kotlin.net.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Yang Jiang on July 12, 2019
 */
@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            NetworkModule::class
        ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
