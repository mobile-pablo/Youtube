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

    override suspend fun upsertSearchHistoryItem(query: String) =
        searchHistoryDao.upsertSearchHistoryItem(
            SearchHistoryEntity(query = query)
        )

    override fun getSearchHistory(): Flow<List<String>> =
        searchHistoryDao.getSearchHistory().map { it.map(SearchHistoryEntity::query) }

    override suspend fun removeSearchHistoryItem(query: String) = searchHistoryDao.removeSearchHistoryItem(query)

    override suspend fun clearSearchHistory() = searchHistoryDao.clearSearchHistory()
}
