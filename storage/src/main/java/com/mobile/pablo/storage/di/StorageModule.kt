package com.mobile.pablo.storage.di

import android.content.Context
import com.mobile.pablo.storage.database.AppDatabase
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManagerImpl
import com.mobile.pablo.storage.source.popular.PopularDataStorage
import com.mobile.pablo.storage.source.popular.PopularDataStorageImpl
import com.mobile.pablo.storage.source.search.SearchDataStorage
import com.mobile.pablo.storage.source.search.SearchDataStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    internal fun providesSharedPreferencesManager(
        impl: SharedPreferencesManagerImpl
    ): SharedPreferencesManager = impl

    @Provides
    @Singleton
    internal fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    internal fun providesSearchDao(
        appDatabase: AppDatabase
    ) = appDatabase.searchDao()

    @Provides
    @Singleton
    internal fun providesPopularDao(
        appDatabase: AppDatabase
    ) = appDatabase.popularDao()

    @Provides

    @Singleton
    internal fun providesPopularDataStorage(
        impl: PopularDataStorageImpl
    ): PopularDataStorage = impl

    @Provides
    @Singleton
    internal fun providesSearchDataStorage(
        impl: SearchDataStorageImpl
    ): SearchDataStorage = impl
}