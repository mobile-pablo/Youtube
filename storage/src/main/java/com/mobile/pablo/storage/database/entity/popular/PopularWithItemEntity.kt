package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import androidx.room.Relation
import com.mobile.pablo.storage.database.const.POPULAR_PREFIX

internal data class PopularWithItemEntity(
    @Embedded(prefix = POPULAR_PREFIX)
    val popular: PopularEntity?,
    @Relation(
        parentColumn = "${POPULAR_PREFIX}etag",
        entityColumn = "parentId"
    )
    val items: List<PopularItemEntity?>?
)