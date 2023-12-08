package com.mobile.pablo.networking.mapper.popular

import com.mobile.pablo.core.model.popular.PopularSnippetDTO
import com.mobile.pablo.networking.mapper.common.LocalizedResponseMapper
import com.mobile.pablo.networking.mapper.common.ThumbnailsResponseMapper
import com.mobile.pablo.networking.model.popular.PopularSnippetResponse
import javax.inject.Inject

internal class PopularSnippetResponseMapper @Inject constructor(
    private val thumbnailsResponseMapper: ThumbnailsResponseMapper,
    private val localizedResponseMapper: LocalizedResponseMapper
) {

    fun map(response: PopularSnippetResponse?): PopularSnippetDTO? {
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
