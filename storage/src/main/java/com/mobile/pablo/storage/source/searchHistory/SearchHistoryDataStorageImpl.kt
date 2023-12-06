package com.mobile.pablo.storage.source.searchHistory

import com.mobile.pablo.storage.database.dao.SearchHistoryDao
import com.mobile.pablo.storage.database.entity.search.SearchHistoryEntity
import javax.inject.Inject

internal class SearchHistoryDataStorageImpl
    @Inject
    constructor(
        private val searchHistoryDao: SearchHistoryDao
    ) : SearchHistoryDataStorage {
        override suspend fun upsertSearchHistory(query: String) =
            searchHistoryDao.upsertSearchHistory(
                SearchHistoryEntity(query = query)
            )

        override suspend fun getSearchHistories(): List<String> =
            searchHistoryDao.getSearchHistories().map(SearchHistoryEntity::query)

        override suspend fun removeSearchHistory(query: String) = searchHistoryDao.removeSearchHistory(query)

        override suspend fun clearSearchHistories() = searchHistoryDao.clearSearchHistories()
    }
