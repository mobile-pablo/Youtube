package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded

internal data class ThumbnailsEntity(
    @Embedded(prefix = "default_") val default: ThumbnailItemEntity,
    @Embedded(prefix = "medium_") val medium: ThumbnailItemEntity,
    @Embedded(prefix = "high_") val high: ThumbnailItemEntity
)