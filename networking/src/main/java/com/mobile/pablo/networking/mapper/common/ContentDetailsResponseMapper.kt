package com.mobile.pablo.networking.mapper.common

import com.mobile.pablo.core.model.common.ContentDetailsDTO
import com.mobile.pablo.networking.model.common.ContentDetailsResponse
import javax.inject.Inject

internal class ContentDetailsResponseMapper @Inject constructor() {

    fun map(response : ContentDetailsResponse?) : ContentDetailsDTO? {
        return response?.run {
            ContentDetailsDTO(
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