package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.storage.database.const.SNIPPET_PREFIX

internal data class PopularItemEntity(
    val kind: String? = null,
    val etag: String = EMPTY_STRING,
    val id: String? = null,
    @Embedded(prefix = SNIPPET_PREFIX) val snippet: PopularSnippetEntity? = null
)