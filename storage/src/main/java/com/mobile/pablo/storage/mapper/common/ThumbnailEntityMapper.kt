package com.mobile.pablo.storage.mapper.common

import com.mobile.pablo.core.model.common.ThumbnailDTO
import com.mobile.pablo.storage.database.entity.common.ThumbnailEntity
import javax.inject.Inject

internal class ThumbnailEntityMapper @Inject constructor() {

    fun map(dto: ThumbnailDTO?): ThumbnailEntity? =
        dto?.run {
            ThumbnailEntity(
                url,
                width,
                height
            )
        }

    fun map(entity: ThumbnailEntity?): ThumbnailDTO? =
        entity?.run {
            ThumbnailDTO(
                url,
                width,
                height
            )
        }
}
