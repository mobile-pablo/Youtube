package com.mobile.pablo.domain.model

data class Snippet(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumbnails? = null,
    val channelTitle: String? = null,
    val liveBroadcastContent: String? = null,
    val publishTime: String? = null
)