package com.mobile.pablo.domain.model

data class PopularVideoSnippet(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val channelTitle: String? = null,
    val tags: List<String> = emptyList(),
    val categoryId: String? = null,
    val liveBroadcastContent: String? = null,
    val localized: Localized? = null,
    val defaultAudioLanguage: String? = null
)