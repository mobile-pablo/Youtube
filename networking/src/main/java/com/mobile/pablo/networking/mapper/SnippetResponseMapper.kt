package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.PopularVideoSnippetDTO
import com.mobile.pablo.core.model.SnippetDTO
import com.mobile.pablo.networking.model.PopularVideoSnippetResponse
import com.mobile.pablo.networking.model.SnippetResponse
import javax.inject.Inject

internal class SnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper,
    private val localizedResponseMapper: LocalizedResponseMapper
) {

    fun mapSearch(response: SnippetResponse?): SnippetDTO? {
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

    fun mapPopularSearch(response: PopularVideoSnippetResponse?): PopularVideoSnippetDTO? {
        return response?.run {
            PopularVideoSnippetDTO(
                publishedAt = publishedAt,
                channelId = channelId,
                title = title,
                description = description,
                thumbnails = thumbnailsResponseMapper.map(thumbnails),
                channelTitle = channelTitle,
                tags = tags,
                categoryId = categoryId,
                liveBroadcastContent = liveBroadcastContent,
                localized = localizedResponseMapper.map(localized),
                defaultAudioLanguage = defaultAudioLanguage
            )
        }
    }
}