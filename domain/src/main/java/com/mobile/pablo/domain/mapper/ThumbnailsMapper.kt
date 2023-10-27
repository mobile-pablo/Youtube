package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.ThumbnailsDTO
import com.mobile.pablo.domain.model.Thumbnails
import javax.inject.Inject

class ThumbnailsMapper @Inject constructor(
    private val thumbnailMapper: ThumbnailMapper
) {

    fun map(dto: ThumbnailsDTO?): Thumbnails? {
        return dto?.run {
            Thumbnails(
                thumbnailMapper.map(default),
                thumbnailMapper.map(medium),
                thumbnailMapper.map(high)
            )
        }
    }
}