package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.PopularSnippetDTO
import com.mobile.pablo.core.model.SearchSnippetDTO
import com.mobile.pablo.domain.model.PopularSnippet
import com.mobile.pablo.domain.model.SearchSnippet
import javax.inject.Inject

class SnippetMapper @Inject constructor(
    private val thumbnailsMapper: ThumbnailsMapper,
    private val localizedMapper: LocalizedMapper
) {

    fun mapSearch(dto: SearchSnippetDTO?): SearchSnippet? {
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

    fun mapPopularSearch(dto: PopularSnippetDTO?): PopularSnippet? {
        return dto?.run {
            PopularSnippet(
                publishedAt = publishedAt,
                channelId = channelId,
                title = title,
                description = description,
                thumbnails = thumbnailsMapper.map(thumbnails),
                channelTitle = channelTitle,
                tags = tags,
                categoryId = categoryId,
                liveBroadcastContent = liveBroadcastContent,
                localized = localizedMapper.map(localized),
                defaultAudioLanguage = defaultAudioLanguage
            )
        }
    }
}