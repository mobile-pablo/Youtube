package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.storage.database.const.PAGE_INFO_PREFIX
import com.mobile.pablo.storage.database.const.SEARCH_TABLE_NAME
import com.mobile.pablo.storage.database.entity.common.PageInfoEntity

@Entity(tableName = SEARCH_TABLE_NAME)
internal data class SearchEntity(
    var kind: String? = null,
    @PrimaryKey var etag: String,
    var nextPageToken: String? = null,
    var prevPageToken: String? = null,
    var regionCode: String? = null,
    @Embedded(prefix = PAGE_INFO_PREFIX)
    var pageInfo: PageInfoEntity? = null
)