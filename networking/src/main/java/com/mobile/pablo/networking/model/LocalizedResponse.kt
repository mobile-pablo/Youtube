package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class LocalizedResponse(
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null
)