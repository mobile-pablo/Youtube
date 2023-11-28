package com.mobile.pablo.networking.model.search

import com.mobile.pablo.networking.model.common.ThumbnailsResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SearchSnippetResponse(
    @Json(name = "publishedAt") val publishedAt: String? = null,
    @Json(name = "channelId") val channelId: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "thumbnails") val thumbnails: ThumbnailsResponse? = null,
    @Json(name = "channelTitle") val channelTitle: String? = null,
    @Json(name = "liveBroadcastContent") val liveBroadcastContent: String? = null,
    @Json(name = "publishTime") val publishTime: String? = null
)
