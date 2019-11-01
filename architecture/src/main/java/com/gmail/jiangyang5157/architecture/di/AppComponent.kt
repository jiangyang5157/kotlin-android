package com.gmail.jiangyang5157.architecture.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

//    fun inject(app: App)
}