package com.gmail.jiangyang5157.kotlin.net

import androidx.annotation.NonNull
import com.gmail.jiangyang5157.architecture.net.LiveDataCallAdapterFactory
import com.gmail.jiangyang5157.kotlin.net.color.ColorService
import com.gmail.jiangyang5157.kotlin.net.color.ColorServiceInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Yang Jiang on July 11, 2019
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(ColorServiceInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideAssetsService(@NonNull okHttpClient: OkHttpClient): ColorService {
        return Retrofit.Builder()
                .baseUrl(ColorService.baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
                .create(ColorService::class.java)
    }
}
