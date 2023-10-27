package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.ThumbnailDTO
import com.mobile.pablo.domain.model.Thumbnail
import javax.inject.Inject

class ThumbnailMapper @Inject constructor() {

    fun map(dto: ThumbnailDTO?): Thumbnail? {
        return dto?.run {
            Thumbnail(
                url,
                width,
                height
            )
        }
    }
}