package com.mobile.pablo.storage.di

import android.content.Context
import com.mobile.pablo.storage.database.AppDatabase
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManager
import com.mobile.pablo.storage.sharedprefs.SharedPreferencesManagerImpl
import com.mobile.pablo.storage.source.search.SearchDataStorage
import com.mobile.pablo.storage.source.search.SearchDataStorageImpl
import com.mobile.pablo.storage.source.searchHistory.SearchHistoryDataStorage
import com.mobile.pablo.storage.source.searchHistory.SearchHistoryDataStorageImpl
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
    internal fun providesSharedPreferencesManager(impl: SharedPreferencesManagerImpl): SharedPreferencesManager = impl

    @Provides
    @Singleton
    internal fun providesAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    internal fun providesSearchDao(appDatabase: AppDatabase) = appDatabase.searchDao()

    @Provides
    @Singleton
    internal fun providesSearchHistoryDao(appDatabase: AppDatabase) = appDatabase.searchHistoryDao()

    @Provides
    @Singleton
    internal fun providesSearchDataStorage(impl: SearchDataStorageImpl): SearchDataStorage = impl

    @Provides
    @Singleton
    internal fun providesSearchHistoryDataStorage(impl: SearchHistoryDataStorageImpl): SearchHistoryDataStorage = impl
}
