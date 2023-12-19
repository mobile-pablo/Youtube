package com.mobile.pablo.networking.mapper.search

import com.mobile.pablo.core.model.search.SearchSnippetDTO
import com.mobile.pablo.networking.mapper.common.ThumbnailsResponseMapper
import com.mobile.pablo.networking.model.search.SearchSnippetResponse
import javax.inject.Inject

internal class SearchSnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper
) {

    fun map(response: SearchSnippetResponse?): SearchSnippetDTO? {
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
