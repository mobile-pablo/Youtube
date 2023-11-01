package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.pablo.storage.database.entity.search.SearchItemEntity

@Dao
internal abstract class SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchItem(search: SearchItemEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchItems(items: List<SearchItemEntity?>)

    @Query("SELECT * FROM search_item")
    abstract suspend fun getSearchItems(): List<SearchItemEntity>?

    @Query("DELETE FROM search_item WHERE etag = :etag")
    abstract suspend fun removeSearchItem(etag: String)

    @Query("DELETE FROM search_item")
    abstract suspend fun clearSearchItems()

    @Query("SELECT * FROM search_item WHERE etag = :etag LIMIT 1")
    abstract suspend fun getSearchItemsByEtag(etag: String): SearchItemEntity?

    @Query("UPDATE search_item SET etag = :etag WHERE etag = :etag")
    abstract suspend fun updateSearchItemsByEtag(etag: String)
}