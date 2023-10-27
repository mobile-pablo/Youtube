package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class IdResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "videoId") val videoId: String? = null
)