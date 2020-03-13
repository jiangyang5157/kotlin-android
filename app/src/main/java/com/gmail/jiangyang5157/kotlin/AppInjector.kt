package com.gmail.jiangyang5157.kotlin

import androidx.lifecycle.ViewModelProvider
import com.gmail.jiangyang5157.architecture.util.AppExecutor
import com.gmail.jiangyang5157.architecture.util.ViewModelFactory
import com.gmail.jiangyang5157.kotlin.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [AppModule::class])
abstract class AppInjector {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppExecutor(): AppExecutor = AppExecutor()
}

