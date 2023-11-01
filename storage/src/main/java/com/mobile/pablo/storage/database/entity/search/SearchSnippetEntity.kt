package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded
import com.mobile.pablo.storage.database.entity.common.ThumbnailsEntity

internal data class SearchSnippetEntity(
    val publishedAt: String?,
    val channelId: String?,
    val title: String?,
    val description: String?,
    @Embedded(prefix = "thumbnails_")
    val thumbnails: ThumbnailsEntity?,
    val channelTitle: String?,
    val liveBroadcastContent: String?,
    val publishTime: String?
)