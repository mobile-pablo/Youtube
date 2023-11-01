package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.SearchSnippetDTO
import com.mobile.pablo.domain.model.SearchSnippet
import javax.inject.Inject

class SearchSnippetMapper @Inject constructor(
    private val thumbnailsMapper: ThumbnailsMapper
) {

    fun map(dto: SearchSnippetDTO?): SearchSnippet? {
        return dto?.run {
            SearchSnippet(
                publishedAt,
                channelId,
                title,
                description,
                thumbnailsMapper.map(thumbnails),
                channelTitle,
                liveBroadcastContent
            )
        }
    }
}