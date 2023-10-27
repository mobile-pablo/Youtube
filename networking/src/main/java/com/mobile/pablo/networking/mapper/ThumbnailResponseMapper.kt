package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.ThumbnailDTO
import com.mobile.pablo.networking.model.ThumbnailResponse
import javax.inject.Inject

internal class ThumbnailResponseMapper @Inject constructor() {

    fun map(response: ThumbnailResponse?): ThumbnailDTO? {
        return response?.run {
            ThumbnailDTO(
                url,
                width,
                height
            )
        }
    }
}