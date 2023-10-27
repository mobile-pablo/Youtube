package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class IdResponse(
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "videoId") var videoId: String? = null
)