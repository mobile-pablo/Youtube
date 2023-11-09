package com.mobile.pablo.networking.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ContentDetailsResponse(
    @Json(name = "duration") val duration: String? = null,
    @Json(name = "dimension") val dimension: String? = null,
    @Json(name = "definition") val definition: String? = null,
    @Json(name = "caption") val caption: String? = null,
    @Json(name = "licensedContent") val licensedContent: Boolean? = null,
    @Json(name = "projection") val projection: String? = null
)