package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.ThumbnailsDTO
import com.mobile.pablo.networking.model.common.ThumbnailsResponse
import javax.inject.Inject

internal class ThumbnailsResponseMapper @Inject constructor(
    private val thumbnailResponseMapper: ThumbnailResponseMapper
) {

    fun map(response: ThumbnailsResponse?): ThumbnailsDTO? {
        return response?.run {
            ThumbnailsDTO(
                thumbnailResponseMapper.map(default),
                thumbnailResponseMapper.map(medium),
                thumbnailResponseMapper.map(high)
            )
        }
    }
}
