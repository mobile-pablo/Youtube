package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.storage.database.const.POPULAR_ITEM_TABLE_NAME
import com.mobile.pablo.storage.database.const.SNIPPET_PREFIX

@Entity(tableName = POPULAR_ITEM_TABLE_NAME)
internal data class PopularItemEntity(
    val kind: String?,
    val parentId: String,
    @PrimaryKey val etag: String = EMPTY_STRING,
    val id: String?,
    @Embedded(prefix = SNIPPET_PREFIX)
    val snippet: PopularSnippetEntity?
)