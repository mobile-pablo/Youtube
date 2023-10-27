package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.ThumbnailDTO
import com.mobile.pablo.networking.model.ThumbnailResponse
import javax.inject.Inject

internal class ThumbnailResponseMapper @Inject constructor() {

    fun map(thumbnail: ThumbnailResponse?): ThumbnailDTO? {
        return thumbnail?.run {
            ThumbnailDTO(
                url,
                width,
                height
            )
        }
    }
}