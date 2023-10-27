package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.models.SnippetDTO
import com.mobile.pablo.networking.model.SnippetResponse
import javax.inject.Inject

internal class SnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper
) {

    fun map(response: SnippetResponse?): SnippetDTO? {
        return response?.run {
            SnippetDTO(
                publishedAt,
                channelId,
                title,
                description,
                thumbnailsResponseMapper.map(thumbnails),
                channelTitle,
                liveBroadcastContent
            )
        }
    }
}