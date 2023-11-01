package com.mobile.pablo.storage.mapper

import com.mobile.pablo.core.model.PopularSnippetDTO
import com.mobile.pablo.storage.database.entity.search.PopularSnippetEntity
import javax.inject.Inject

internal class PopularSnippetEntityMapper @Inject constructor(
    private val thumbnailsEntityMapper: ThumbnailsEntityMapper,
    private val localizedEntityMapper: LocalizedEntityMapper
) {

    fun map(dto: PopularSnippetDTO?): PopularSnippetEntity? {
        return dto?.run {
            PopularSnippetEntity(
                publishedAt = publishedAt,
                channelId = channelId,
                title = title,
                description = description,
                thumbnails = thumbnailsEntityMapper.map(thumbnails),
                channelTitle = channelTitle,
                tags = tags,
                categoryId = categoryId,
                liveBroadcastContent = liveBroadcastContent,
                localized = localizedEntityMapper.map(localized),
                defaultAudioLanguage = defaultAudioLanguage
            )
        }
    }

    fun map(entity: PopularSnippetEntity?): PopularSnippetDTO? {
        return entity?.run {
            PopularSnippetDTO(
                publishedAt = publishedAt,
                channelId = channelId,
                title = title,
                description = description,
                thumbnails = thumbnailsEntityMapper.map(thumbnails),
                channelTitle = channelTitle,
                tags = tags,
                categoryId = categoryId,
                liveBroadcastContent = liveBroadcastContent,
                localized = localizedEntityMapper.map(localized),
                defaultAudioLanguage = defaultAudioLanguage
            )
        }
    }
}