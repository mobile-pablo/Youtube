package com.mobile.pablo.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.pablo.storage.database.const.POPULAR_TABLE_NAME
import com.mobile.pablo.storage.database.entity.popular.PopularEntity

@Dao
internal abstract class PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopular(search: PopularEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopulars(items: List<PopularEntity?>)

    @Query("SELECT * FROM $POPULAR_TABLE_NAME")
    abstract suspend fun getPopular(): PopularEntity?

    @Query("DELETE FROM $POPULAR_TABLE_NAME WHERE etag = :etag")
    abstract suspend fun removePopular(etag: String)

    @Query("DELETE FROM $POPULAR_TABLE_NAME")
    abstract suspend fun clearPopulars()

    @Query("SELECT * FROM $POPULAR_TABLE_NAME WHERE etag = :etag LIMIT 1")
    abstract suspend fun getPopularByEtag(etag: String): PopularEntity?

    @Query("UPDATE $POPULAR_TABLE_NAME SET etag = :etag WHERE etag = :etag")
    abstract suspend fun updatePopularByEtag(etag: String)
}