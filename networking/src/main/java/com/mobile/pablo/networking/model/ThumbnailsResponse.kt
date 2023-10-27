package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class ThumbnailsResponse(
    @Json(name = "default") var default: ThumbnailResponse? = null,
    @Json(name = "medium") var medium: ThumbnailResponse? = null,
    @Json(name = "high") var high: ThumbnailResponse? = null
)

data class ThumbnailResponse(
    @Json(name = "url") var url: String? = null,
    @Json(name = "width") var width: Int? = null,
    @Json(name = "height") var height: Int? = null
)