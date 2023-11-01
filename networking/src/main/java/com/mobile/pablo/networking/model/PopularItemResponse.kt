package com.mobile.pablo.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class PopularItemResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String,
    @Json(name = "id") val id: String? = null,
    @Json(name = "snippet") val snippet: PopularSnippetResponse? = null
)