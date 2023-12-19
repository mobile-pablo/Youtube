package com.mobile.pablo.domain.mapper.search

import com.mobile.pablo.core.model.search.SearchSnippetDTO
import com.mobile.pablo.domain.mapper.common.ThumbnailsMapper
import com.mobile.pablo.domain.model.search.SearchSnippet
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
