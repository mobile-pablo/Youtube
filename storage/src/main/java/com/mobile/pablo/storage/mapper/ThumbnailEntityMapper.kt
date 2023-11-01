package com.mobile.pablo.storage.mapper

import com.mobile.pablo.core.model.ThumbnailDTO
import com.mobile.pablo.storage.database.entity.search.ThumbnailEntity
import javax.inject.Inject

internal class ThumbnailEntityMapper @Inject constructor() {

    fun map(dto: ThumbnailDTO?): ThumbnailEntity? {
        return dto?.run {
            ThumbnailEntity(
                url,
                width,
                height
            )
        }
    }

    fun map(entity: ThumbnailEntity?): ThumbnailDTO? {
        return entity?.run {
            ThumbnailDTO(
                url,
                width,
                height
            )
        }
    }
}