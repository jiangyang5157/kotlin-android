package com.gmail.jiangyang5157.example_core

import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.core.util.AppExecutor
import com.gmail.jiangyang5157.core.util.ViewModelFactory
import com.gmail.jiangyang5157.example_core.ui.MainActivity
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
}

@Module()
abstract class AppInjection {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
