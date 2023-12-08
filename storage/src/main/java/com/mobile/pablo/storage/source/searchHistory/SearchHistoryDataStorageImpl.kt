package com.mobile.pablo.storage.source.searchHistory

import com.mobile.pablo.storage.database.dao.SearchHistoryDao
import com.mobile.pablo.storage.database.entity.search.SearchHistoryEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SearchHistoryDataStorageImpl
    @Inject
    constructor(
        private val searchHistoryDao: SearchHistoryDao
    ) : SearchHistoryDataStorage {
        override suspend fun upsertSearchHistory(query: String) =
            searchHistoryDao.upsertSearchHistory(
                SearchHistoryEntity(query = query)
            )

        override fun getSearchHistories(): Flow<List<String>> =
            searchHistoryDao.getSearchHistories().map { it.map(SearchHistoryEntity::query) }

        override suspend fun removeSearchHistory(query: String) = searchHistoryDao.removeSearchHistory(query)

        override suspend fun clearSearchHistories() = searchHistoryDao.clearSearchHistories()
    }
