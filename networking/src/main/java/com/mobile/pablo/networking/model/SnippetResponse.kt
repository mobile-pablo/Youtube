package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SnippetResponse(
    @Json(name = "publishedAt") var publishedAt: String? = null,
    @Json(name = "channelId") var channelId: String? = null,
    @Json(name = "title") var title: String? = null,
    @Json(name = "description") var description: String? = null,
    @Json(name = "thumbnails") var thumbnails: ThumbnailsResponse? = null,
    @Json(name = "channelTitle") var channelTitle: String? = null,
    @Json(name = "liveBroadcastContent") var liveBroadcastContent: String? = null,
    @Json(name = "publishTime") var publishTime: String? = null
)
