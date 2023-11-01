package com.mobile.pablo.storage.database.entity.search

import androidx.room.Embedded

internal data class PopularSnippetEntity(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    @Embedded(prefix = "thumbnails") val thumbnails: ThumbnailsEntity? = null,
    val channelTitle: String? = null,
    val tags: List<String> = emptyList(),
    val categoryId: String? = null,
    val liveBroadcastContent: String? = null,
    @Embedded(prefix = "localized_") val localized: LocalizedEntity? = null,
    val defaultAudioLanguage: String? = null
)