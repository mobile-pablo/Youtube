package com.mobile.pablo.core.model

data class SnippetDTO(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: ThumbnailsDTO? = null,
    val channelTitle: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: String? = null,
    val tags: ArrayList<String> = arrayListOf(),
    val categoryId: String? = null,
    val localized: LocalizedDTO? = null,
    val defaultAudioLanguage: String? = null
)