package com.mobile.pablo.storage.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mobile.pablo.storage.database.const.POPULAR_ITEM_TABLE_NAME
import com.mobile.pablo.storage.database.const.POPULAR_TABLE_NAME
import com.mobile.pablo.storage.database.entity.popular.PopularEntity
import com.mobile.pablo.storage.database.entity.popular.PopularItemEntity
import com.mobile.pablo.storage.database.entity.popular.PopularWithItemEntity

@Dao
internal abstract class PopularDao {

    @Upsert
    abstract suspend fun upsertPopular(search: PopularEntity?)

    @Upsert
    abstract suspend fun upsertPopularItems(items: List<PopularItemEntity?>)

    @Query("SELECT * FROM $POPULAR_TABLE_NAME")
    abstract suspend fun getPopular(): PopularEntity?

    @Query(
        "SELECT * FROM $POPULAR_TABLE_NAME" +
                " INNER JOIN $POPULAR_ITEM_TABLE_NAME" +
                " ON $POPULAR_TABLE_NAME.etag = $POPULAR_ITEM_TABLE_NAME.parentId"
    )
    abstract suspend fun pagingPopular(): PagingSource<String, PopularWithItemEntity>

    @Query("SELECT * FROM $POPULAR_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun getPopularItems(parentId: String): List<PopularItemEntity?>?

    @Query("DELETE FROM $POPULAR_TABLE_NAME WHERE etag = :etag")
    abstract suspend fun removePopular(etag: String)

    @Query("DELETE FROM $POPULAR_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun removePopularItems(parentId: String)

    @Query("DELETE FROM $POPULAR_TABLE_NAME")
    abstract suspend fun clearPopulars()

    @Query("DELETE FROM $POPULAR_ITEM_TABLE_NAME")
    abstract suspend fun clearPopularItems()

    @Query("SELECT * FROM $POPULAR_TABLE_NAME WHERE etag = :etag LIMIT 1")
    abstract suspend fun getPopularByEtag(etag: String): PopularEntity?

    @Query("SELECT * FROM $POPULAR_ITEM_TABLE_NAME WHERE parentId = :parentId")
    abstract suspend fun getPopularItemsByParentEtag(parentId: String): List<PopularItemEntity?>?

    @Transaction
    open suspend fun getPopularWithItems(): PopularWithItemEntity? {
        val popular = getPopular()
        val items = getPopularItems(popular!!.etag)
        return PopularWithItemEntity(popular, items)
    }

    @Transaction
    open suspend fun getPopularWithItemsByEtag(etag: String): PopularWithItemEntity? {
        val popular = getPopularByEtag(etag)
        val items = getPopularItemsByParentEtag(etag)
        return PopularWithItemEntity(popular, items)
    }

    @Transaction
    open suspend fun upsertPopularWithItems(popularWithItemEntity: PopularWithItemEntity) {
        upsertPopular(popularWithItemEntity.popular)
        upsertPopularItems(popularWithItemEntity.items!!)
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