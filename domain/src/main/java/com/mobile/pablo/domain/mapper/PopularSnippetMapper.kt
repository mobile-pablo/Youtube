package com.mobile.pablo.domain.mapper

import com.mobile.pablo.core.model.PopularSnippetDTO
import com.mobile.pablo.domain.model.PopularSnippet
import javax.inject.Inject

class PopularSnippetMapper @Inject constructor(
    private val thumbnailsMapper: ThumbnailsMapper,
    private val localizedMapper: LocalizedMapper
) {

    fun map(dto: PopularSnippetDTO?): PopularSnippet? {
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