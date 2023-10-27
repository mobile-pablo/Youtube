package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SearchItemResponse(
    @Json(name = "kind") val kind: String? = null,
    @Json(name = "etag") val etag: String? = null,
    @Json(name = "id") val id: IdResponse? = null,
    @Json(name = "snippet") val snippet: SnippetResponse? = null
)