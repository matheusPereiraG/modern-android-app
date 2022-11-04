package com.android.app.di

import com.android.app.BuildConfig
import com.android.app.network.ProductsService
import com.android.app.network.UserAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): UserAuthService =
        retrofit.create(UserAuthService::class.java)

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

}