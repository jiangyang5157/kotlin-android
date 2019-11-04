package com.gmail.jiangyang5157.kotlin.app

import com.gmail.jiangyang5157.kotlin.ui.main.MainActivity
import com.gmail.jiangyang5157.kotlin.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
