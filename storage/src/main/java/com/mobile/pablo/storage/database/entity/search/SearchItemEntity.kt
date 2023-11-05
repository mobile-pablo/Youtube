package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import com.mobile.pablo.storage.database.const.ID_PREFIX
import com.mobile.pablo.storage.database.const.SNIPPET_PREFIX
import com.mobile.pablo.storage.database.entity.common.IdEntity

internal data class SearchItemEntity(
    val kind: String?,
    val etag: String,
    @Embedded(prefix = ID_PREFIX) val id: IdEntity?,
    @Embedded(prefix = SNIPPET_PREFIX) val snippet: SearchSnippetEntity?
)