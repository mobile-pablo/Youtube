package com.mobile.pablo.storage.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mobile.pablo.storage.database.const.SEARCH_HISTORY_TABLE_NAME
import com.mobile.pablo.storage.database.entity.search.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
internal abstract class SearchHistoryDao {
    @Upsert
    abstract suspend fun upsertSearchHistory(searchHistory: SearchHistoryEntity)

    @Query("SELECT * FROM $SEARCH_HISTORY_TABLE_NAME")
    abstract fun getSearchHistories(): Flow<List<SearchHistoryEntity>>

    @Query("DELETE FROM $SEARCH_HISTORY_TABLE_NAME WHERE query = :query")
    abstract suspend fun removeSearchHistory(query: String)

    @Query("DELETE FROM $SEARCH_HISTORY_TABLE_NAME")
    abstract suspend fun clearSearchHistories()
}
