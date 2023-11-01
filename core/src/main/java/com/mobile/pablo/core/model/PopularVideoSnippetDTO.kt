package com.mobile.pablo.core.model

data class PopularVideoSnippetDTO(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: ThumbnailsDTO? = null,
    val channelTitle: String? = null,
    val tags: List<String> = emptyList(),
    val categoryId: String? = null,
    val liveBroadcastContent: String? = null,
    val localized: LocalizedDTO? = null,
    val defaultAudioLanguage: String? = null
)