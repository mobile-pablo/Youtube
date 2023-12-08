package com.mobile.pablo.storage.source.searchHistory

import kotlinx.coroutines.flow.Flow

interface SearchHistoryDataStorage {
    suspend fun upsertSearchHistory(query: String)

    fun getSearchHistories(): Flow<List<String>>

    suspend fun removeSearchHistory(query: String)

    suspend fun clearSearchHistories()
}
