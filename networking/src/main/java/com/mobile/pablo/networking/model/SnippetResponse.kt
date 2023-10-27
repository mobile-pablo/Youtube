package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SnippetResponse(
    @Json(name = "publishedAt") val publishedAt: String? = null,
    @Json(name = "channelId") val channelId: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "thumbnails") val thumbnails: ThumbnailsResponse? = null,
    @Json(name = "channelTitle") val channelTitle: String? = null,
    @Json(name = "liveBroadcastContent") val liveBroadcastContent: String? = null,
    @Json(name = "publishTime") val publishTime: String? = null,
    @Json(name = "tags") val tags: ArrayList<String> = arrayListOf(),
    @Json(name = "categoryId") val categoryId: String? = null,
    @Json(name = "localized") val localized: LocalizedResponse? = null,
    @Json(name = "defaultAudioLanguage") val defaultAudioLanguage: String? = null
)