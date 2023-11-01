package com.mobile.pablo.networking.mapper

import com.mobile.pablo.core.model.PopularSnippetDTO
import com.mobile.pablo.core.model.SearchSnippetDTO
import com.mobile.pablo.networking.model.PopularSnippetResponse
import com.mobile.pablo.networking.model.SnippetResponse
import javax.inject.Inject

internal class SnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper,
    private val localizedResponseMapper: LocalizedResponseMapper
) {

    fun mapSearch(response: SnippetResponse?): SearchSnippetDTO? {
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

    fun mapPopularSearch(response: PopularSnippetResponse?): PopularSnippetDTO? {
        return response?.run {
            PopularSnippetDTO(
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