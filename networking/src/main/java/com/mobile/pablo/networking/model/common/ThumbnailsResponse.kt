package com.mobile.pablo.networking.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ThumbnailsResponse(
    @Json(name = "default") val default: ThumbnailResponse? = null,
    @Json(name = "medium") val medium: ThumbnailResponse? = null,
    @Json(name = "high") val high: ThumbnailResponse? = null
)
