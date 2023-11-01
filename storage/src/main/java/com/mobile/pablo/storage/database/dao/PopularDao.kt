package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.pablo.storage.database.entity.popular.PopularItemEntity

@Dao
internal abstract class PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularItem(search: PopularItemEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularItems(items: List<PopularItemEntity?>)

    @Query("SELECT * FROM popular_item")
    abstract suspend fun getPopularItems(): List<PopularItemEntity>?

    @Query("DELETE FROM popular_item WHERE etag = :etag")
    abstract suspend fun removePopularItem(etag: String)

    @Query("DELETE FROM popular_item")
    abstract suspend fun clearPopularItems()

    @Query("SELECT * FROM popular_item WHERE etag = :etag LIMIT 1")
    abstract suspend fun getPopularItemsByEtag(etag: String): PopularItemEntity?

    @Query("UPDATE popular_item SET etag = :etag WHERE etag = :etag")
    abstract suspend fun updatePopularItemsByEtag(etag: String)
}