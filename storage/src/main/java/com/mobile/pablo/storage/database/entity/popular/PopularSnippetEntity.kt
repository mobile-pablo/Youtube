package com.mobile.pablo.storage.database.entity.popular

import androidx.room.Embedded
import com.mobile.pablo.storage.database.const.LOCALIZED_PREFIX
import com.mobile.pablo.storage.database.const.THUMBNAILS_PREFIX
import com.mobile.pablo.storage.database.entity.common.LocalizedEntity
import com.mobile.pablo.storage.database.entity.common.ThumbnailsEntity

internal data class PopularSnippetEntity(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    @Embedded(prefix = THUMBNAILS_PREFIX) val thumbnails: ThumbnailsEntity? = null,
    val channelTitle: String? = null,
    val tags: List<String> = emptyList(),
    val categoryId: String? = null,
    val liveBroadcastContent: String? = null,
    @Embedded(prefix = LOCALIZED_PREFIX) val localized: LocalizedEntity? = null,
    val defaultAudioLanguage: String? = null
)