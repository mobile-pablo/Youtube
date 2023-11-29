package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.storage.database.const.ID_PREFIX
import com.mobile.pablo.storage.database.const.SEARCH_ITEM_TABLE_NAME
import com.mobile.pablo.storage.database.const.SNIPPET_PREFIX
import com.mobile.pablo.storage.database.entity.common.IdEntity

@Entity(tableName = SEARCH_ITEM_TABLE_NAME)
internal data class SearchItemEntity(
    val kind: String?,
    @PrimaryKey val etag: String,
    val parentId: String?,
    @Embedded(prefix = ID_PREFIX) val id: IdEntity?,
    @Embedded(prefix = SNIPPET_PREFIX) val snippet: SearchSnippetEntity?
)
