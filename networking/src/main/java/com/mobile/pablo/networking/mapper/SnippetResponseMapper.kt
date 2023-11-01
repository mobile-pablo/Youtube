package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.SearchSnippetDTO
import com.mobile.pablo.networking.model.SnippetResponse
import javax.inject.Inject

internal class SnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper
) {

    fun map(response: SnippetResponse?): SearchSnippetDTO? {
        return response?.run {
            SearchSnippetDTO(
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