package com.mobile.pablo.networking.model

import com.squareup.moshi.Json

data class SearchItemResponse(
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "etag") var etag: String? = null,
    @Json(name = "id") var id: IdResponse? = null,
    @Json(name = "snippet") var snippet: SnippetResponse? = null
)
