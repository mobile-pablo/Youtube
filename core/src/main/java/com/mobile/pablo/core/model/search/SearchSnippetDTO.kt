package com.mobile.pablo.core.model.search

import com.mobile.pablo.core.model.common.ThumbnailsDTO

data class SearchSnippetDTO(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: ThumbnailsDTO? = null,
    val channelTitle: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: String? = null
)