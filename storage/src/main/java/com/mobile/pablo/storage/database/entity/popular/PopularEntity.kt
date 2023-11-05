package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.storage.database.const.ITEMS_PREFIX
import com.mobile.pablo.storage.database.const.PAGE_INFO_PREFIX
import com.mobile.pablo.storage.database.const.POPULAR_TABLE_NAME
import com.mobile.pablo.storage.database.entity.common.PageInfoEntity

@Entity(tableName = POPULAR_TABLE_NAME)
internal data class PopularEntity(
    var kind: String? = null,
    @PrimaryKey var etag: String,
    var nextPageToken: String? = null,
    var prevPageToken: String? = null,
    @Embedded(prefix = PAGE_INFO_PREFIX)
    var pageInfo: PageInfoEntity? = null,
    @Embedded(prefix = ITEMS_PREFIX)
    var items: List<PopularItemEntity?> = emptyList()
)