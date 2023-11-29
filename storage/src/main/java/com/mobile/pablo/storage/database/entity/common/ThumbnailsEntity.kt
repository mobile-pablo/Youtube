package com.mobile.pablo.storage.database.entity.common

import androidx.room.Embedded
import com.mobile.pablo.storage.database.const.DEFAULT_PREFIX
import com.mobile.pablo.storage.database.const.HIGH_PREFIX
import com.mobile.pablo.storage.database.const.MEDIUM_PREFIX

internal data class ThumbnailsEntity(
    @Embedded(prefix = DEFAULT_PREFIX) val default: ThumbnailEntity?,
    @Embedded(prefix = MEDIUM_PREFIX) val medium: ThumbnailEntity?,
    @Embedded(prefix = HIGH_PREFIX) val high: ThumbnailEntity?
)
