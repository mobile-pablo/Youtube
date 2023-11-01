package com.mobile.pablo.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class SearchItemResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String,
    @Json(name = "id") val id: IdResponse? = null,
    @Json(name = "snippet") val snippet: SnippetResponse? = null
)