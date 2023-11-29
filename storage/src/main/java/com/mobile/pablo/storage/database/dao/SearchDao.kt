package com.mobile.pablo.storage.database.dao

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mobile.pablo.storage.database.const.SEARCH_ITEM_TABLE_NAME
import com.mobile.pablo.storage.database.const.SEARCH_TABLE_NAME
import com.mobile.pablo.storage.database.entity.search.SearchEntity
import com.mobile.pablo.storage.database.entity.search.SearchItemEntity
import com.mobile.pablo.storage.database.entity.search.SearchWithItemEntity

@Keep
@Dao
internal abstract class SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearch(search: SearchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertSearchItems(items: List<SearchItemEntity>)

    @Query("SELECT * FROM $SEARCH_TABLE_NAME")
    abstract suspend fun getSearch(): SearchEntity?

    @Query("SELECT * FROM $SEARCH_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun getSearchItems(parentId: String): List<SearchItemEntity>?

    @Query("DELETE FROM $SEARCH_TABLE_NAME WHERE etag = :etag")
    abstract suspend fun removeSearch(etag: String)

    @Query("DELETE FROM $SEARCH_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun removeSearchItems(parentId: String)

    @Query("DELETE FROM $SEARCH_TABLE_NAME")
    abstract suspend fun clearSearches()

    @Query("DELETE FROM $SEARCH_ITEM_TABLE_NAME")
    abstract suspend fun clearSearchItems()

    @Query("SELECT * FROM $SEARCH_TABLE_NAME WHERE etag = :etag LIMIT 1")
    abstract suspend fun getSearchByEtag(etag: String): SearchEntity?

    @Query("SELECT * FROM $SEARCH_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun getSearchItemsByParentEtag(parentId: String): List<SearchItemEntity>?

    @Transaction
    open suspend fun getSearchWithItems(): SearchWithItemEntity? {
        val search = getSearch()
        val items = search?.etag?.let { getSearchItems(it) }
        return SearchWithItemEntity(search, items)
    }

    @Transaction
    open suspend fun getSearchWithItemsByEtag(etag: String): SearchWithItemEntity? {
        val search = getSearchByEtag(etag)!!
        val items = getSearchItemsByParentEtag(etag)!!
        return SearchWithItemEntity(search, items)
    }

    @Transaction
    open suspend fun insertSearchWithItems(searchWithItemEntity: SearchWithItemEntity) {
        insertSearch(searchWithItemEntity.search!!)
        insertSearchItems(searchWithItemEntity.items!!)
    }

    @Transaction
    open suspend fun removeSearchWithItems(etag: String) {
        removeSearch(etag)
        removeSearchItems(etag)
    }

    @Transaction
    open suspend fun clearSearchesWithItems() {
        clearSearches()
        clearSearchItems()
    }
}
