package com.mobile.pablo.storage.mapper

import com.mobile.pablo.core.model.ThumbnailsDTO
import com.mobile.pablo.storage.database.entity.search.ThumbnailsEntity
import javax.inject.Inject

internal class ThumbnailsEntityMapper @Inject constructor(
    private val thumbnailEntityMapper: ThumbnailEntityMapper
) {

    fun map(dto: ThumbnailsDTO?): ThumbnailsEntity? {
        return dto?.run {
            ThumbnailsEntity(
                thumbnailEntityMapper.map(default),
                thumbnailEntityMapper.map(medium),
                thumbnailEntityMapper.map(high)
            )
        }
    }

    fun map(entity: ThumbnailsEntity?): ThumbnailsDTO? {
        return entity?.run {
            ThumbnailsDTO(
                thumbnailEntityMapper.map(default),
                thumbnailEntityMapper.map(medium),
                thumbnailEntityMapper.map(high)
            )
        }
    }
}