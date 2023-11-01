package com.mobile.pablo.networking.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class LocalizedResponse(
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null
)