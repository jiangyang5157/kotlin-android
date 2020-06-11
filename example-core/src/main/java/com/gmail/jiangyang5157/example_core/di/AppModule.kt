package com.gmail.jiangyang5157.example_core.di

import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.core.util.ViewModelFactory
import com.gmail.jiangyang5157.example_core.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [AppInjection::class])
class AppModule

@Module()
abstract class AppInjection {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
