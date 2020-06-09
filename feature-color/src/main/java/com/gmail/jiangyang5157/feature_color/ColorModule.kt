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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ColorInjection::class])
class ColorModule constructor(private val parameter: Any) {

    @Provides
    @Singleton
    @Named("Dummy")
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ColorServiceInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideColorService(@Named("Dummy") @NonNull okHttpClient: OkHttpClient): ColorService {
        return Retrofit.Builder()
            .baseUrl(ColorService.baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ColorService::class.java)
    }
}

@Module
abstract class ColorInjection {

    @ContributesAndroidInjector(
        modules = [
            ColorViewModelInjection::class
        ]
    )
    abstract fun contributeColorFragment(): ColorFragment

    @Binds
    abstract fun bindColorRepository(ColorRepository: ColorRepositoryImpl): ColorRepository
}

@Module
abstract class ColorViewModelInjection {

    @Binds
    @IntoMap
    @ViewModelKey(ColorViewModel::class)
    abstract fun bindColorViewModel(viewModel: ColorViewModel): ViewModel
}
