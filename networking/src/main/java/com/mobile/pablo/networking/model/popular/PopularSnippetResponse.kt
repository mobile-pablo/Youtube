package com.mobile.pablo.networking.model.popular

import com.mobile.pablo.networking.model.common.LocalizedResponse
import com.mobile.pablo.networking.model.common.ThumbnailsResponse
import com.squareup.moshi.Json

internal data class PopularSnippetResponse(
    @Json(name = "publishedAt") val publishedAt: String? = null,
    @Json(name = "channelId") val channelId: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "thumbnails") val thumbnails: ThumbnailsResponse? = null,
    @Json(name = "channelTitle") val channelTitle: String? = null,
    @Json(name = "tags") val tags: List<String> = emptyList(),
    @Json(name = "categoryId") val categoryId: String? = null,
    @Json(name = "liveBroadcastContent") val liveBroadcastContent: String? = null,
    @Json(name = "localized") val localized: LocalizedResponse? = null,
    @Json(name = "defaultAudioLanguage") val defaultAudioLanguage: String? = null
)
