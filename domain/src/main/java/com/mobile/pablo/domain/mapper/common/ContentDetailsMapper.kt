package com.mobile.pablo.domain.mapper.common

import com.mobile.pablo.core.model.common.ContentDetailsDTO
import com.mobile.pablo.domain.model.common.ContentDetails
import javax.inject.Inject

class ContentDetailsMapper @Inject constructor() {

    fun map(dto : ContentDetailsDTO?) : ContentDetails? {
        return dto?.run {
            ContentDetails(
                duration = duration,
                dimension = dimension,
                definition = definition,
                caption = caption,
                licensedContent = licensedContent,
                projection = projection
            )
        }
    }
}