package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import androidx.room.Relation
import com.mobile.pablo.storage.database.const.SEARCH_PREFIX

internal data class SearchWithItemEntity(
    @Embedded(prefix = SEARCH_PREFIX)
    val search: SearchEntity?,
    @Relation(
        parentColumn = "${SEARCH_PREFIX}etag",
        entityColumn = "parentId"
    )
    val items: List<SearchItemEntity>?
)
