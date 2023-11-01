package com.mobile.pablo.storage.di

import android.content.Context
import com.mobile.pablo.storage.database.AppDatabase
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
}