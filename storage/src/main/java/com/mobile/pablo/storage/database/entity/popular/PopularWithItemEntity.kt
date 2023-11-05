package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import androidx.room.Relation

internal data class PopularWithItemEntity(
    @Embedded
    val popular: PopularEntity?,
    @Relation(
        parentColumn = "etag",
        entityColumn = "parentId"
    )
    val items: List<PopularItemEntity?>?
)
