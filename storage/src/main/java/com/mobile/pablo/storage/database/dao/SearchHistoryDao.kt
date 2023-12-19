package com.mobile.pablo.storage.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mobile.pablo.storage.database.const.SEARCH_HISTORY_LIMIT
import com.mobile.pablo.storage.database.const.SEARCH_HISTORY_TABLE_NAME
import com.mobile.pablo.storage.database.entity.search.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
internal abstract class SearchHistoryDao {

    @Upsert
    abstract suspend fun upsertSearchHistoryItem(searchHistory: SearchHistoryEntity)

    @Query("SELECT * FROM $SEARCH_HISTORY_TABLE_NAME")
    abstract fun getSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Query("SELECT * FROM $SEARCH_HISTORY_TABLE_NAME")
    abstract fun getSearchHistoryList(): List<SearchHistoryEntity>

    @Query("DELETE FROM $SEARCH_HISTORY_TABLE_NAME WHERE query = :query")
    abstract suspend fun removeSearchHistoryItem(query: String)

    @Query("DELETE FROM $SEARCH_HISTORY_TABLE_NAME")
    abstract suspend fun clearSearchHistory()

    @Transaction
    open suspend fun upsertNotDuplicateSearchHistoryItem(searchHistory: SearchHistoryEntity) {
        val searchHistoryEntityList = getSearchHistoryList().takeLast(SEARCH_HISTORY_LIMIT)
        searchHistoryEntityList.forEach {
            if (it.query == searchHistory.query) {
                return
            }
        }
        upsertSearchHistoryItem(searchHistory)
    }
}
