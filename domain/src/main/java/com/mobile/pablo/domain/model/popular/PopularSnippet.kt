package com.mobile.pablo.domain.model.popular

import com.mobile.pablo.domain.model.common.Localized
import com.mobile.pablo.domain.model.common.Thumbnails

data class PopularSnippet(
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