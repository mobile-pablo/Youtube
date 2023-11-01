package com.mobile.pablo.storage.database.entity.common

import androidx.room.Embedded

internal data class ThumbnailsEntity(
    @Embedded(prefix = "default_") val default: ThumbnailEntity?,
    @Embedded(prefix = "medium_") val medium: ThumbnailEntity?,
    @Embedded(prefix = "high_") val high: ThumbnailEntity?
)