package com.gmail.jiangyang5157.example_core.di

import com.gmail.jiangyang5157.core.util.AppExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppExecutor(): AppExecutor {
        return AppExecutor()
    }
}
