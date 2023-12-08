package com.mobile.pablo.storage.source.searchHistory

import kotlinx.coroutines.flow.Flow

interface SearchHistoryDataStorage {
    suspend fun upsertSearchHistoryItem(query: String)

    fun getSearchHistory(): Flow<List<String>>

    suspend fun removeSearchHistoryItem(query: String)

    suspend fun clearSearchHistory()
}
