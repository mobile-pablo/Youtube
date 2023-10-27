package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class ThumbnailsResponse(
    @Json(name = "default") val default: ThumbnailResponse? = null,
    @Json(name = "medium") val medium: ThumbnailResponse? = null,
    @Json(name = "high") val high: ThumbnailResponse? = null
)

data class ThumbnailResponse(
    @Json(name = "url") val url: String? = null,
    @Json(name = "width") val width: Int? = null,
    @Json(name = "height") val height: Int? = null
)