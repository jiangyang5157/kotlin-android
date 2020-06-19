package com.gmail.jiangyang5157.feature_color.di

import com.gmail.jiangyang5157.feature_color.data.DefaultColorRepository
import com.gmail.jiangyang5157.feature_color.data.service.ColorService
import com.gmail.jiangyang5157.feature_color.domain.repo.ColorRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ColorServiceModule {

    @Singleton
    @Provides
    fun provideColorService(): ColorService {
        return ColorService.Builder().build()
    }
}

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ColorDataModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindColorRepository(colorRepository: DefaultColorRepository): ColorRepository
}
