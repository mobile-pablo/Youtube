package com.mobile.pablo.storage.source.searchHistory

interface SearchHistoryDataStorage {
    suspend fun upsertSearchHistory(query: String)

    suspend fun getSearchHistories(): List<String>

    suspend fun removeSearchHistory(query: String)

    suspend fun clearSearchHistories()
}
