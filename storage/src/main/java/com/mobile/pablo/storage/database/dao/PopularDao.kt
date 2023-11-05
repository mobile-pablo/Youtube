package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mobile.pablo.storage.database.const.POPULAR_ITEM_TABLE_NAME
import com.mobile.pablo.storage.database.const.POPULAR_TABLE_NAME
import com.mobile.pablo.storage.database.entity.popular.PopularEntity
import com.mobile.pablo.storage.database.entity.popular.PopularItemEntity
import com.mobile.pablo.storage.database.entity.popular.PopularWithItemEntity

@Dao
internal abstract class PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopular(search: PopularEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularItems(items: List<PopularItemEntity?>)

    @Query("SELECT * FROM $POPULAR_TABLE_NAME")
    abstract suspend fun getPopular(): PopularWithItemEntity?

    @Query("DELETE FROM $POPULAR_TABLE_NAME WHERE etag = :etag")
    abstract suspend fun removePopular(etag: String)

    @Query("DELETE FROM $POPULAR_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun removePopularItems(parentId: String)

    @Query("DELETE FROM $POPULAR_TABLE_NAME")
    abstract suspend fun clearPopulars()

    @Query("DELETE FROM $POPULAR_ITEM_TABLE_NAME")
    abstract suspend fun clearPopularItems()

    @Query("SELECT * FROM $POPULAR_TABLE_NAME WHERE etag = :etag LIMIT 1")
    abstract suspend fun getPopularByEtag(etag: String): PopularWithItemEntity?

    @Transaction
    open suspend fun insertPopularWithItems(popularWithItemEntity: PopularWithItemEntity) {
        insertPopular(popularWithItemEntity.popular)
        insertPopularItems(popularWithItemEntity.items!!)
    }

    @Transaction
    open suspend fun removePopularWithItems(etag: String) {
        removePopular(etag)
        removePopularItems(etag)
    }

    @Transaction
    open suspend fun clearPopularsWithItems() {
        clearPopulars()
        clearPopularItems()
    }
}