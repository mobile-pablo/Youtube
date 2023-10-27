package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.ThumbnailsDTO
import com.mobile.pablo.networking.model.ThumbnailsResponse
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