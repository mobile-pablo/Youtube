package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.ThumbnailDTO
import com.mobile.pablo.core.util.EMPTY_STRING
import com.mobile.pablo.networking.const.IMAGE_PREFIX
import com.mobile.pablo.networking.model.common.ThumbnailResponse
import javax.inject.Inject

internal class ThumbnailResponseMapper @Inject constructor() {

    fun map(response: ThumbnailResponse?): ThumbnailDTO? {
        return response?.run {
            ThumbnailDTO(
                url.takeIf { it!!.startsWith(IMAGE_PREFIX) } ?: EMPTY_STRING,
                width,
                height
            )
        }
    }
}
