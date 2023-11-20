package com.mobile.pablo.networking.di

import android.content.Context
import androidx.annotation.Keep
import com.mobile.pablo.networking.BuildConfig
import com.mobile.pablo.networking.const.TIMEOUT_MILLIS
import com.mobile.pablo.networking.interceptor.RequestInterceptor
import com.mobile.pablo.networking.service.YoutubeService
import com.mobile.pablo.networking.source.popular.PopularDataSource
import com.mobile.pablo.networking.source.popular.PopularDataSourceImpl
import com.mobile.pablo.networking.source.search.SearchDataSource
import com.mobile.pablo.networking.source.search.SearchDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun providesRequestInterceptor(): RequestInterceptor = RequestInterceptor()

    @Provides
    @Singleton
    fun providesCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        val cacheDir = File(context.cacheDir, "http-cache")
        return Cache(cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: RequestInterceptor,
        cache: Cache
    ): OkHttpClient =
        OkHttpClient.Builder()
            .cache(cache)
            .callTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    @Keep
    fun providesRetrofit(
        client: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    internal fun providesYoutubeService(retrofit: Retrofit): YoutubeService =
        retrofit.create(YoutubeService::class.java)

    @Provides
    @Singleton
    internal fun providesSearchDataSource(impl: SearchDataSourceImpl): SearchDataSource = impl

    @Provides
    @Singleton
    internal fun providesPopularDataSource(impl: PopularDataSourceImpl): PopularDataSource = impl
}