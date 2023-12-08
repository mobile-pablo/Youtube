package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.ThumbnailDTO
import com.mobile.pablo.domain.model.common.Thumbnail
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
