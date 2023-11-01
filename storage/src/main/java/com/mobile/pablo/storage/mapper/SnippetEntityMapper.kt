package com.mobile.pablo.storage.mapper

import com.mobile.pablo.core.model.SearchSnippetDTO
import com.mobile.pablo.storage.database.entity.search.SearchSnippetEntity
import javax.inject.Inject

internal class SnippetEntityMapper @Inject constructor(
    private val thumbnailsMapper: ThumbnailsEntityMapper
) {

    fun map(dto: SearchSnippetDTO?): SearchSnippetEntity? {
        return dto?.run {
            SearchSnippetEntity(
                publishedAt,
                channelId,
                title,
                description,
                thumbnailsMapper.map(thumbnails),
                channelTitle,
                liveBroadcastContent,
                publishTime
            )
        }
    }

    fun map(entity: SearchSnippetEntity?): SearchSnippetDTO? {
        return entity?.run {
            SearchSnippetDTO(
                publishedAt,
                channelId,
                title,
                description,
                thumbnailsMapper.map(thumbnails),
                channelTitle,
                liveBroadcastContent,
                publishTime
            )
        }
    }
}