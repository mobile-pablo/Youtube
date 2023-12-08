package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.ThumbnailDTO
import com.mobile.pablo.networking.model.common.ThumbnailResponse
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
