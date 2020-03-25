package com.gmail.jiangyang5157.feature_color

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import com.gmail.jiangyang5157.core.network.LiveDataCallAdapterFactory
import com.gmail.jiangyang5157.core.util.ViewModelKey
import com.gmail.jiangyang5157.feature_color.data.repository.ColorRepositoryImpl
import com.gmail.jiangyang5157.feature_color.domain.repository.ColorRepository
import com.gmail.jiangyang5157.feature_color.service.ColorService
import com.gmail.jiangyang5157.feature_color.service.ColorServiceInterceptor
import com.gmail.jiangyang5157.feature_color.ui.ColorFragment
import com.gmail.jiangyang5157.feature_color.vm.ColorViewModel
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ColorModule::class])
abstract class ColorInjector {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeColorFragment(): ColorFragment

    @Binds
    @IntoMap
    @ViewModelKey(ColorViewModel::class)
    abstract fun bindColorViewModel(viewModel: ColorViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindColorRepository(ColorRepository: ColorRepositoryImpl): ColorRepository
}

@Module
class ColorModule {

    @Provides
    @Singleton
    @Named("Mocked")
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ColorServiceInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideColorService(@Named("Mocked") @NonNull okHttpClient: OkHttpClient): ColorService {
        return Retrofit.Builder()
            .baseUrl(ColorService.baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ColorService::class.java)
    }
}