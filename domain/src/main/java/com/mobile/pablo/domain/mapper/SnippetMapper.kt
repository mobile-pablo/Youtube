package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.SnippetDTO
import com.mobile.pablo.domain.model.Snippet
import javax.inject.Inject

internal class SnippetMapper @Inject constructor(
    private val thumbnailsMapper: ThumbnailsMapper,
    private val localizedMapper: LocalizedMapper
) {

    fun mapSearch(dto: SnippetDTO?): Snippet? {
        return dto?.run {
            Snippet(
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

    fun mapPopularSearch(dto: SnippetDTO?): Snippet? {
        return dto?.run {
            Snippet(
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