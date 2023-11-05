package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.pablo.storage.database.const.SEARCH_TABLE_NAME
import com.mobile.pablo.storage.database.entity.search.SearchWithItemEntity

@Dao
internal abstract class SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearch(search: SearchWithItemEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearches(items: List<SearchWithItemEntity?>)

    @Query("SELECT * FROM $SEARCH_TABLE_NAME")
    abstract suspend fun getSearch(): SearchWithItemEntity?

    @Query("DELETE FROM $SEARCH_TABLE_NAME WHERE etag = :etag")
    abstract suspend fun removeSearch(etag: String)

    @Query("DELETE FROM $SEARCH_TABLE_NAME")
    abstract suspend fun clearSearches()

    @Query("SELECT * FROM $SEARCH_TABLE_NAME WHERE etag = :etag LIMIT 1")
    abstract suspend fun getSearchByEtag(etag: String): SearchWithItemEntity?

    @Query("UPDATE $SEARCH_TABLE_NAME SET etag = :etag WHERE etag = :etag")
    abstract suspend fun updateSearchByEtag(etag: String)
}