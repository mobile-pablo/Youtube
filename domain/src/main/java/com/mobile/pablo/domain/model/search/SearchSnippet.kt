package com.mobile.pablo.domain.model.search

import com.mobile.pablo.domain.model.common.Thumbnails

data class SearchSnippet(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val channelTitle: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: String? = null
)